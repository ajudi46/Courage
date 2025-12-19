package com.courage.app.data.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "decisions",
    indices = [
        Index(value = ["createdAtEpochMs"]),
        Index(value = ["theme"]),
    ],
)
data class DecisionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val scenarioId: String,
    val theme: String,
    val chosenSide: String,
    val chosenPosture: String,
    val timeToDecisionMs: Long,
    val isAvoidantChoice: Boolean,
    val createdAtEpochMs: Long,
)


