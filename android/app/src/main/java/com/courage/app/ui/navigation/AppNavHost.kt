package com.courage.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.courage.app.ui.screens.auth.AuthScreen
import com.courage.app.ui.screens.auth.AuthViewModel
import com.courage.app.ui.screens.decision.DecisionScreen
import com.courage.app.ui.screens.decision.DecisionViewModel
import com.courage.app.ui.screens.feedback.FeedbackScreen
import com.courage.app.ui.screens.feedback.FeedbackViewModel
import com.courage.app.ui.screens.gate.GateViewModel
import com.courage.app.ui.screens.onboarding.OnboardingScreen
import com.courage.app.ui.screens.onboarding.OnboardingViewModel
import com.courage.app.ui.screens.sessionend.SessionEndScreen
import com.courage.app.ui.screens.sessionend.SessionEndViewModel
import com.courage.app.ui.screens.sessionsummary.SessionSummaryScreen
import com.courage.app.ui.screens.sessionsummary.SessionSummaryViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Gate,
    ) {
        composable(Routes.Gate) {
            val vm: GateViewModel = hiltViewModel()
            val gate = vm.gateState.collectAsState().value
            LaunchedEffect(gate) {
                when (gate) {
                    GateViewModel.GateState.Loading -> Unit
                    GateViewModel.GateState.NeedAuth ->
                        navController.navigate(Routes.Auth) { popUpTo(Routes.Gate) { inclusive = true } }
                    GateViewModel.GateState.NeedOnboarding ->
                        navController.navigate(Routes.Onboarding) { popUpTo(Routes.Gate) { inclusive = true } }
                    GateViewModel.GateState.Ready ->
                        navController.navigate(Routes.Decision) { popUpTo(Routes.Gate) { inclusive = true } }
                }
            }
        }

        composable(Routes.Auth) {
            val vm: AuthViewModel = hiltViewModel()
            AuthScreen(
                viewModel = vm,
                onAuthed = {
                    navController.navigate(Routes.Gate) {
                        popUpTo(Routes.Auth) { inclusive = true }
                    }
                },
            )
        }

        composable(Routes.Onboarding) {
            val vm: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                viewModel = vm,
                onComplete = {
                    navController.navigate(Routes.Decision) {
                        popUpTo(Routes.Onboarding) { inclusive = true }
                    }
                },
            )
        }

        composable(Routes.Decision) {
            val vm: DecisionViewModel = hiltViewModel()
            DecisionScreen(
                viewModel = vm,
                onNavigateToFeedback = { decisionId -> navController.navigate(Routes.feedback(decisionId)) },
                onNavigateToSessionEnd = {
                    navController.navigate(Routes.SessionEnd) {
                        popUpTo(Routes.Decision) { inclusive = true }
                    }
                },
                onNavigateToSessionSummary = {
                    navController.navigate(Routes.SessionSummary) {
                        popUpTo(Routes.Decision) { inclusive = true }
                    }
                },
            )
        }

        composable(
            route = "${Routes.Feedback}/{${Routes.ArgDecisionId}}",
            arguments = listOf(navArgument(Routes.ArgDecisionId) { type = NavType.LongType }),
        ) {
            val vm: FeedbackViewModel = hiltViewModel()
            FeedbackScreen(
                viewModel = vm,
                onContinue = {
                    navController.navigate(Routes.Decision) {
                        popUpTo(Routes.Feedback) { inclusive = true }
                    }
                },
            )
        }

        composable(Routes.SessionEnd) {
            val vm: SessionEndViewModel = hiltViewModel()
            SessionEndScreen(viewModel = vm)
        }

        composable(Routes.SessionSummary) {
            val vm: SessionSummaryViewModel = hiltViewModel()
            SessionSummaryScreen(
                viewModel = vm,
                onDone = {
                    // Keep it calm: return to gate so it can re-route (likely back here) without showing scenarios.
                    navController.navigate(Routes.Gate) {
                        popUpTo(Routes.SessionSummary) { inclusive = true }
                    }
                },
            )
        }
    }
}


