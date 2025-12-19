package com.courage.app.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scenarios")
data class ScenarioEntity(
    @PrimaryKey val id: String,
    val theme: String,
    val context: String,
    val role: String,
    val powerDynamic: String,
    val stakesLevel: String,
    val tensionAxis: String,
    val scenarioText: String,
    val optionLeftText: String,
    val optionRightText: String,
    val optionLeftPosture: String,
    val optionRightPosture: String,
    val avoidanceIndicator: String,
    val primarySignalTracked: String,
)


