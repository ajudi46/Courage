package com.courage.app.domain.courage

import com.courage.app.domain.model.DecisionEvent
import com.courage.app.domain.model.DecisionTheme

/**
 * Conservative MVP heuristics:
 * - Never decay on a single hesitation or a single avoidant choice.
 * - Only ever signals "decay" (by 1) when a short pattern is repeating.
 *
 * This is intentionally simple but PRD-aligned: patterns > events.
 */
object AvoidanceDecayDetector {
    data class Inputs(
        val newDecisionIsHesitant: Boolean,
        val newDecisionIsAvoidant: Boolean,
        val newDecisionTheme: DecisionTheme,
        val recent: List<DecisionEvent>, // most recent first
    )

    fun shouldDecay(inputs: Inputs): Boolean {
        val recent3 = inputs.recent.take(3)

        // Pattern 1: repeated hesitation (needs current hesitation + at least 2 in last 3)
        val priorHesitations = recent3.count { it.timeToDecisionMs >= HESITATION_MS }
        if (inputs.newDecisionIsHesitant && priorHesitations >= 2) return true

        // Pattern 2: repeated avoidant posture within same theme (needs current avoidant + at least 2 recent avoidant same theme)
        val priorAvoidantSameTheme = recent3.count { it.theme == inputs.newDecisionTheme && it.isAvoidantChoice }
        if (inputs.newDecisionIsAvoidant && priorAvoidantSameTheme >= 2) return true

        // Pattern 3: chronic safety bias (5+ decisions with very high avoidant ratio, slow and conservative)
        val window5 = inputs.recent.take(5)
        if (window5.size >= 5) {
            val avoidantRatio = window5.count { it.isAvoidantChoice }.toDouble() / window5.size.toDouble()
            if (avoidantRatio >= 0.8 && inputs.newDecisionIsAvoidant) return true
        }

        return false
    }

    const val HESITATION_MS: Long = 3500
}


