package com.courage.app.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.courage.app.domain.model.ChoiceSide
import com.courage.app.domain.model.DecisionPosture
import com.courage.app.domain.model.DecisionTheme
import com.courage.app.domain.model.Scenario
import com.courage.app.ui.screens.auth.AuthScreenContent
import com.courage.app.ui.screens.decision.DecisionScreenContent
import com.courage.app.ui.screens.decision.DecisionViewModel
import com.courage.app.ui.screens.feedback.FeedbackScreenContent
import com.courage.app.ui.screens.feedback.FeedbackViewModel
import com.courage.app.ui.screens.onboarding.OnboardingScreenContent
import com.courage.app.ui.screens.onboarding.OnboardingViewModel
import com.courage.app.ui.screens.sessionend.SessionEndScreenContent
import com.courage.app.ui.screens.sessionsummary.SessionSummaryScreenContent
import com.courage.app.ui.screens.sessionsummary.SessionSummaryViewModel
import com.courage.app.ui.theme.CourageTheme

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun Preview_Auth() {
    CourageTheme {
        AuthScreenContent(
            errorMessage = null,
            onContinueWithGoogle = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 900)
@Composable
private fun Preview_Onboarding() {
    CourageTheme {
        OnboardingScreenContent(
            state = OnboardingViewModel.UiState(
                ageRange = "25–34",
                occupation = "Technology / Software",
                role = "Product Manager",
                experienceLevel = "3–5 years",
                preferredContext = "Office",
                isSaving = false,
                error = null,
            ),
            onAgeRange = {},
            onOccupation = {},
            onRole = {},
            onExperience = {},
            onPreferredContext = {},
            onContinue = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun Preview_Decision() {
    CourageTheme {
        DecisionScreenContent(
            state = DecisionViewModel.UiState(
                isLoading = false,
                scenario = sampleScenario(),
                error = null,
                courage = 4,
                sessionCompleted = false,
            ),
            onRetry = {},
            onSwipe = {},
            onNavigateToSessionSummary = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun Preview_Feedback() {
    CourageTheme {
        FeedbackScreenContent(
            state = FeedbackViewModel.UiState(
                isLoading = false,
                title = "You chose.",
                chosenText = "Make a call with available data",
                mayProtect = "momentum and a clear next step",
                mayRisk = "not exploring every possible angle",
                courage = 3,
                error = null,
            ),
            onContinue = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 900)
@Composable
private fun Preview_SessionSummary() {
    CourageTheme {
        SessionSummaryScreenContent(
            state = SessionSummaryViewModel.UiState(
                isLoading = false,
                courageRemaining = 2,
                decisionsCount = 20,
                avgTimeMs = 2300,
                themeStats = listOf(
                    SessionSummaryViewModel.ThemeStats(
                        theme = DecisionTheme.SpeedVsPerfection,
                        decisions = 8,
                        avgTimeMs = 1800,
                        avoidantRatePct = 40,
                        poleLeftLabel = "Speed",
                        poleRightLabel = "Perfection/Delay",
                        poleLeftPct = 60,
                        poleRightPct = 40,
                    ),
                    SessionSummaryViewModel.ThemeStats(
                        theme = DecisionTheme.ConflictVsComfort,
                        decisions = 6,
                        avgTimeMs = 2600,
                        avoidantRatePct = 66,
                        poleLeftLabel = "Comfort",
                        poleRightLabel = "Conflict",
                        poleLeftPct = 66,
                        poleRightPct = 34,
                    ),
                ),
                error = null,
            ),
            onDone = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 700)
@Composable
private fun Preview_SessionEnd() {
    CourageTheme {
        SessionEndScreenContent(courageRemaining = 0)
    }
}

private fun sampleScenario(): Scenario {
    return Scenario(
        id = "SVSP-03",
        theme = DecisionTheme.SpeedVsPerfection,
        context = "Office",
        role = "Manager",
        powerDynamic = "Authority",
        stakesLevel = "Medium",
        tensionAxis = "Act \u2190\u2192 WaitForMoreData",
        scenarioText = "A decision is blocking progress, but data is incomplete.",
        optionLeftText = "Make a call with available data",
        optionRightText = "Ask for more analysis",
        optionLeftPosture = DecisionPosture.Decisive,
        optionRightPosture = DecisionPosture.RiskAverse,
        avoidanceIndicator = ChoiceSide.Right,
        primarySignalTracked = "DecisionLatency",
    )
}


