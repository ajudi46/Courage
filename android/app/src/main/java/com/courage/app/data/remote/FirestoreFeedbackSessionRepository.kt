package com.courage.app.data.remote

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class FirestoreFeedbackSessionRepository(
    private val firestore: FirebaseFirestore,
) : FeedbackSessionRepository {
    override suspend fun upsertDaily(uid: String, summary: FeedbackSessionSummary) {
        val doc = firestore.collection(FirestorePaths.Users)
            .document(uid)
            .collection(FirestorePaths.FeedbackSessions)
            .document(summary.sessionId)

        val themeBreakdown = summary.themeStats.associate { t ->
            t.theme.name to mapOf(
                "decisions" to t.decisions,
                "avgTimeMs" to t.avgTimeMs,
                "avoidantRatePct" to t.avoidantRatePct,
                "poleLeftLabel" to t.poleLeftLabel,
                "poleLeftPct" to t.poleLeftPct,
                "poleRightLabel" to t.poleRightLabel,
                "poleRightPct" to t.poleRightPct,
            )
        }

        val payload = mapOf(
            "endedAt" to FieldValue.serverTimestamp(),
            "decisionsCount" to summary.decisionsCount,
            "avgTimeMs" to summary.avgTimeMs,
            "courageRemaining" to summary.courageRemaining,
            "themeBreakdown" to themeBreakdown,
        )

        doc.set(payload, SetOptions.merge()).await()
    }
}


