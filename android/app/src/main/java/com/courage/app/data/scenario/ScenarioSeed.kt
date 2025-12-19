package com.courage.app.data.scenario

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScenarioSeed(
    val id: String,
    val theme: String,
    val context: String,
    val role: String,
    val powerDynamic: String,
    val stakesLevel: String,
    val tensionAxis: String,
    val optionLeftText: String,
    val optionRightText: String,
    val optionLeftPosture: String,
    val optionRightPosture: String,
    val avoidanceIndicator: String,
    val primarySignalTracked: String,
    val scenarioText: String,
)


