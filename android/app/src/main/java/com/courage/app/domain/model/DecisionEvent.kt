package com.courage.app.domain.model

data class DecisionEvent(
    val scenarioId: String,
    val theme: DecisionTheme,
    val chosenSide: ChoiceSide,
    val chosenPosture: DecisionPosture,
    val timeToDecisionMs: Long,
    val isAvoidantChoice: Boolean,
    val createdAtEpochMs: Long,
)


