package com.courage.app.ui.navigation

object Routes {
    const val Gate = "gate"
    const val Auth = "auth"
    const val Onboarding = "onboarding"
    const val Decision = "decision"
    const val Feedback = "feedback"
    const val SessionEnd = "session_end"
    const val SessionSummary = "session_summary"

    const val ArgDecisionId = "decisionId"
    fun feedback(decisionId: Long): String = "$Feedback/$decisionId"
}


