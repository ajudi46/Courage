package com.courage.app.ui.screens.feedback

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courage.app.data.decision.DecisionRepository
import com.courage.app.data.scenario.ScenarioRepository
import com.courage.app.data.session.CourageRepository
import com.courage.app.domain.feedback.FeedbackComposer
import com.courage.app.domain.model.ChoiceSide
import com.courage.app.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val decisionRepository: DecisionRepository,
    private val scenarioRepository: ScenarioRepository,
    courageRepository: CourageRepository,
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = true,
        val title: String = "You chose.",
        val chosenText: String = "",
        val mayProtect: String = "",
        val mayRisk: String = "",
        val courage: Int = 5,
        val error: String? = null,
    )

    private val decisionId: Long =
        requireNotNull(savedStateHandle.get<Long>(Routes.ArgDecisionId)) { "Missing decisionId" }

    private val _state = MutableStateFlow(UiState(courage = courageRepository.courageState.value.current))
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            runCatching {
                val decision = decisionRepository.getById(decisionId) ?: error("Missing decision")
                val scenario = scenarioRepository.getScenarioById(decision.scenarioId) ?: error("Missing scenario")

                val chosenText = when (decision.chosenSide) {
                    ChoiceSide.Left -> scenario.optionLeftText
                    ChoiceSide.Right -> scenario.optionRightText
                }
                val tradeOff = FeedbackComposer.tradeOffFor(decision.chosenPosture)
                UiState(
                    isLoading = false,
                    title = "You chose.",
                    chosenText = chosenText,
                    mayProtect = tradeOff.mayProtect,
                    mayRisk = tradeOff.mayRisk,
                    courage = _state.value.courage,
                    error = null,
                )
            }.onSuccess { _state.value = it }
                .onFailure { e -> _state.value = _state.value.copy(isLoading = false, error = e.message ?: "Could not load feedback.") }
        }
    }
}


