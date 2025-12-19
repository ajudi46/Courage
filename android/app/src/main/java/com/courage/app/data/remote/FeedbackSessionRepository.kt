package com.courage.app.data.remote

import com.courage.app.ui.screens.sessionsummary.SessionSummaryViewModel

data class FeedbackSessionSummary(
    val sessionId: String,
    val decisionsCount: Int,
    val avgTimeMs: Long,
    val courageRemaining: Int,
    val themeStats: List<SessionSummaryViewModel.ThemeStats>,
)

interface FeedbackSessionRepository {
    suspend fun upsertDaily(uid: String, summary: FeedbackSessionSummary)
}


