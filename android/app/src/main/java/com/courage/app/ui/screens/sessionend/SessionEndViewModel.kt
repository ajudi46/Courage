package com.courage.app.ui.screens.sessionend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courage.app.data.auth.AuthRepository
import com.courage.app.data.decision.DecisionRepository
import com.courage.app.data.remote.FeedbackSessionRepository
import com.courage.app.data.remote.FeedbackSessionSummary
import com.courage.app.data.session.CourageRepository
import com.courage.app.domain.model.DecisionPosture
import com.courage.app.domain.model.DecisionTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class SessionEndViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val decisionRepository: DecisionRepository,
    private val courageRepository: CourageRepository,
    private val feedbackSessionRepository: FeedbackSessionRepository,
) : ViewModel() {

    data class UiState(
        val courageRemaining: Int = 0,
    )

    private val _state = MutableStateFlow(
        UiState(courageRemaining = courageRepository.courageState.value.current),
    )
    val state: StateFlow<UiState> = _state

    init {
        // Best-effort: log the session feedback even when Courage hits 0.
        viewModelScope.launch {
            val uid = authRepository.currentUser.value?.uid ?: return@launch
            val (startMs, endMs) = todayBoundsEpochMs()
            val todays = decisionRepository.getBetween(startMs, endMs).take(20)
            if (todays.isEmpty()) return@launch

            val avg = todays.map { it.timeToDecisionMs }.average().toLong()
            val themeStats = todays.groupBy { it.theme }.map { (theme, items) ->
                val themeAvg = items.map { it.timeToDecisionMs }.average().toLong()
                val avoidantRate = (items.count { it.isAvoidantChoice }.toDouble() / items.size.toDouble() * 100.0).toInt()

                val pole = poleConfig(theme)
                val leftCount = items.count { pole.isLeftPole(it.chosenPosture, it.isAvoidantChoice) }
                val leftPct = (leftCount.toDouble() / items.size.toDouble() * 100.0).toInt()
                val rightPct = (100 - leftPct).coerceIn(0, 100)

                com.courage.app.ui.screens.sessionsummary.SessionSummaryViewModel.ThemeStats(
                    theme = theme,
                    decisions = items.size,
                    avgTimeMs = themeAvg,
                    avoidantRatePct = avoidantRate,
                    poleLeftLabel = pole.leftLabel,
                    poleRightLabel = pole.rightLabel,
                    poleLeftPct = leftPct,
                    poleRightPct = rightPct,
                )
            }

            runCatching {
                feedbackSessionRepository.upsertDaily(
                    uid = uid,
                    summary = FeedbackSessionSummary(
                        sessionId = sessionIdToday(),
                        decisionsCount = todays.size,
                        avgTimeMs = avg,
                        courageRemaining = courageRepository.courageState.value.current,
                        themeStats = themeStats,
                    ),
                )
            }
        }
    }

    private fun todayBoundsEpochMs(zoneId: ZoneId = ZoneId.systemDefault()): Pair<Long, Long> {
        val start = LocalDate.now(zoneId).atStartOfDay(zoneId).toInstant().toEpochMilli()
        val end = LocalDate.now(zoneId).plusDays(1).atStartOfDay(zoneId).toInstant().toEpochMilli()
        return start to end
    }

    private fun sessionIdToday(zoneId: ZoneId = ZoneId.systemDefault()): String {
        return LocalDate.now(zoneId).format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE)
    }

    private data class PoleConfig(
        val leftLabel: String,
        val rightLabel: String,
        val leftPostures: Set<DecisionPosture>,
        val rightPostures: Set<DecisionPosture>,
    ) {
        fun isLeftPole(posture: DecisionPosture, isAvoidantChoice: Boolean): Boolean {
            return when {
                leftPostures.contains(posture) -> true
                rightPostures.contains(posture) -> false
                else -> !isAvoidantChoice
            }
        }
    }

    private fun poleConfig(theme: DecisionTheme): PoleConfig {
        return when (theme) {
            DecisionTheme.SpeedVsPerfection -> PoleConfig(
                leftLabel = "Speed",
                rightLabel = "Perfection/Delay",
                leftPostures = setOf(DecisionPosture.Decisive),
                rightPostures = setOf(DecisionPosture.Overthinking, DecisionPosture.RiskAverse, DecisionPosture.Avoidant),
            )
            DecisionTheme.ConflictVsComfort -> PoleConfig(
                leftLabel = "Comfort",
                rightLabel = "Conflict",
                leftPostures = setOf(DecisionPosture.Avoidant, DecisionPosture.ApprovalSeeking, DecisionPosture.Submissive),
                rightPostures = setOf(DecisionPosture.Assertive, DecisionPosture.OwnershipHeavy),
            )
            DecisionTheme.OwnershipVsSafety -> PoleConfig(
                leftLabel = "Safety",
                rightLabel = "Ownership",
                leftPostures = setOf(DecisionPosture.Avoidant, DecisionPosture.Defensive),
                rightPostures = setOf(DecisionPosture.OwnershipHeavy),
            )
            DecisionTheme.AutonomyVsApproval -> PoleConfig(
                leftLabel = "Approval",
                rightLabel = "Autonomy",
                leftPostures = setOf(DecisionPosture.ApprovalSeeking, DecisionPosture.Avoidant),
                rightPostures = setOf(DecisionPosture.Autonomous),
            )
            DecisionTheme.AuthorityVsAssertion -> PoleConfig(
                leftLabel = "Defer/Control",
                rightLabel = "Assert/Empower",
                leftPostures = setOf(DecisionPosture.Submissive, DecisionPosture.Avoidant, DecisionPosture.Controlling),
                rightPostures = setOf(DecisionPosture.Assertive, DecisionPosture.Empowering),
            )
        }
    }
}


