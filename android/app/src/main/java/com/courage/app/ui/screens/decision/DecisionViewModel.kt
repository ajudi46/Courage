package com.courage.app.ui.screens.decision

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courage.app.data.decision.DecisionRepository
import com.courage.app.data.scenario.ScenarioRepository
import com.courage.app.data.session.CourageRepository
import com.courage.app.domain.courage.AvoidanceDecayDetector
import com.courage.app.domain.model.ChoiceSide
import com.courage.app.domain.model.DecisionEvent
import com.courage.app.domain.model.Scenario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class DecisionViewModel @Inject constructor(
    private val scenarioRepository: ScenarioRepository,
    private val decisionRepository: DecisionRepository,
    private val courageRepository: CourageRepository,
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = true,
        val scenario: Scenario? = null,
        val error: String? = null,
        val courage: Int = 5,
        val sessionCompleted: Boolean = false,
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    private var shownAtEpochMs: Long? = null

    init {
        viewModelScope.launch {
            courageRepository.ensureDailyReset(LocalDate.now())
            _state.value = _state.value.copy(courage = courageRepository.courageState.value.current)
            loadScenario()
        }
    }

    fun loadScenario() {
        viewModelScope.launch {
            // If the user already completed today's 20 decisions, do not serve more scenarios.
            if (hasCompletedTodaysSession()) {
                _state.value = _state.value.copy(isLoading = false, scenario = null, error = null, sessionCompleted = true)
                return@launch
            }
            _state.value = _state.value.copy(isLoading = true, error = null, sessionCompleted = false)
            runCatching {
                scenarioRepository.getNextScenario()
            }.onSuccess { scenario ->
                shownAtEpochMs = System.currentTimeMillis()
                _state.value = _state.value.copy(isLoading = false, scenario = scenario)
            }.onFailure { e ->
                _state.value = _state.value.copy(isLoading = false, error = e.message ?: "Could not load scenario.")
            }
        }
    }

    fun onChoice(
        side: ChoiceSide,
        onNavigateToFeedback: (decisionId: Long) -> Unit,
        onNavigateToSessionEnd: () -> Unit,
        onNavigateToSessionSummary: () -> Unit,
    ) {
        val scenario = _state.value.scenario ?: return
        val now = System.currentTimeMillis()
        val ttd = (shownAtEpochMs?.let { now - it } ?: 0L).coerceAtLeast(0)

        viewModelScope.launch {
            // Ensure daily reset is enforced (no shame across days).
            courageRepository.ensureDailyReset(LocalDate.now())

            val recentBefore = decisionRepository.getRecent(limit = 5)

            val chosenPosture = when (side) {
                ChoiceSide.Left -> scenario.optionLeftPosture
                ChoiceSide.Right -> scenario.optionRightPosture
            }
            val avoidantSide = scenario.avoidanceIndicator
            val isAvoidant = side == avoidantSide

            val event = DecisionEvent(
                scenarioId = scenario.id,
                theme = scenario.theme,
                chosenSide = side,
                chosenPosture = chosenPosture,
                timeToDecisionMs = ttd,
                isAvoidantChoice = isAvoidant,
                createdAtEpochMs = now,
            )

            val decisionId = decisionRepository.recordDecision(event)

            // Decide whether Courage should decay (pattern-based, rate-limited, max -1).
            val shouldDecay = AvoidanceDecayDetector.shouldDecay(
                AvoidanceDecayDetector.Inputs(
                    newDecisionIsHesitant = ttd >= AvoidanceDecayDetector.HESITATION_MS,
                    newDecisionIsAvoidant = isAvoidant,
                    newDecisionTheme = scenario.theme,
                    recent = recentBefore,
                ),
            )

            val before = courageRepository.courageState.value.current
            val didDecrement = if (shouldDecay) {
                courageRepository.decrementOnceIfAllowed(
                    nowEpochMs = now,
                    cooldownMs = 2 * 60 * 1000L, // 2 min cooldown; avoids punitive-feeling drops
                )
            } else false
            val after = (before - (if (didDecrement) 1 else 0)).coerceIn(0, 5)
            _state.value = _state.value.copy(courage = after)

            // Session cap: 20 decisions/day.
            if (todaysDecisionCountIncludingThis(nowEpochMs = now) >= 20) {
                onNavigateToSessionSummary()
            } else if (after <= 0) {
                onNavigateToSessionEnd()
            } else {
                onNavigateToFeedback(decisionId)
            }
        }
    }

    private suspend fun hasCompletedTodaysSession(): Boolean {
        val (startMs, endMs) = todayBoundsEpochMs()
        val todays = decisionRepository.getBetween(startMs, endMs)
        return todays.size >= 20
    }

    private suspend fun todaysDecisionCountIncludingThis(nowEpochMs: Long): Int {
        val (startMs, endMs) = todayBoundsEpochMs(nowEpochMs)
        val todays = decisionRepository.getBetween(startMs, endMs)
        return todays.size
    }

    private fun todayBoundsEpochMs(nowEpochMs: Long = System.currentTimeMillis(), zoneId: ZoneId = ZoneId.systemDefault()): Pair<Long, Long> {
        val today = java.time.Instant.ofEpochMilli(nowEpochMs).atZone(zoneId).toLocalDate()
        val start = today.atStartOfDay(zoneId).toInstant().toEpochMilli()
        val end = today.plusDays(1).atStartOfDay(zoneId).toInstant().toEpochMilli()
        return start to end
    }
}


