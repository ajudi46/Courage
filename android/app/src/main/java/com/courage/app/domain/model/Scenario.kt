package com.courage.app.domain.model

data class Scenario(
    val id: String,
    val theme: DecisionTheme,
    val context: String,
    val role: String,
    val powerDynamic: String,
    val stakesLevel: String,
    val tensionAxis: String,
    val scenarioText: String,
    val optionLeftText: String,
    val optionRightText: String,
    val optionLeftPosture: DecisionPosture,
    val optionRightPosture: DecisionPosture,
    val avoidanceIndicator: ChoiceSide,
    val primarySignalTracked: String,
)


