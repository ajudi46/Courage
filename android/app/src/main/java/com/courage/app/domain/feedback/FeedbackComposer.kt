package com.courage.app.domain.feedback

import com.courage.app.domain.model.DecisionPosture

data class TradeOff(
    val mayProtect: String,
    val mayRisk: String,
)

object FeedbackComposer {
    fun tradeOffFor(posture: DecisionPosture): TradeOff {
        return when (posture) {
            DecisionPosture.Avoidant ->
                TradeOff(
                    mayProtect = "short-term comfort and reduced friction",
                    mayRisk = "delaying clarity or leaving the tension unresolved",
                )
            DecisionPosture.ApprovalSeeking ->
                TradeOff(
                    mayProtect = "social harmony and acceptance",
                    mayRisk = "losing your own preference in the decision",
                )
            DecisionPosture.Submissive ->
                TradeOff(
                    mayProtect = "stability and reduced pushback",
                    mayRisk = "your needs staying unspoken",
                )
            DecisionPosture.Overthinking ->
                TradeOff(
                    mayProtect = "confidence in the details",
                    mayRisk = "stalling action while uncertainty grows",
                )
            DecisionPosture.RiskAverse ->
                TradeOff(
                    mayProtect = "safety and predictability",
                    mayRisk = "slower momentum and prolonged ambiguity",
                )
            DecisionPosture.Defensive ->
                TradeOff(
                    mayProtect = "your self-image in the moment",
                    mayRisk = "missing a chance to repair trust",
                )
            DecisionPosture.Controlling ->
                TradeOff(
                    mayProtect = "speed and control over outcomes",
                    mayRisk = "others feeling less ownership",
                )
            DecisionPosture.Assertive ->
                TradeOff(
                    mayProtect = "clarity and directness",
                    mayRisk = "temporary discomfort or tension",
                )
            DecisionPosture.OwnershipHeavy ->
                TradeOff(
                    mayProtect = "trust through responsibility",
                    mayRisk = "carrying more accountability",
                )
            DecisionPosture.Autonomous ->
                TradeOff(
                    mayProtect = "alignment with your judgment",
                    mayRisk = "disagreement or disapproval",
                )
            DecisionPosture.Decisive ->
                TradeOff(
                    mayProtect = "momentum and a clear next step",
                    mayRisk = "not exploring every possible angle",
                )
            DecisionPosture.Empowering ->
                TradeOff(
                    mayProtect = "shared ownership and buy-in",
                    mayRisk = "a slower decision in the short term",
                )
        }
    }
}


