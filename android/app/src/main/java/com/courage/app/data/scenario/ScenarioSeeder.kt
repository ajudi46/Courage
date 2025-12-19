package com.courage.app.data.scenario

import android.content.Context
import com.courage.app.data.db.dao.ScenarioDao
import com.courage.app.data.db.entities.ScenarioEntity
import kotlinx.serialization.json.Json

class ScenarioSeeder(
    private val context: Context,
    private val scenarioDao: ScenarioDao,
) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun seedIfNeeded() {
        if (scenarioDao.count() > 0) return

        val raw = context.assets.open("scenarios_mvp.json").bufferedReader().use { it.readText() }
        val seeds = json.decodeFromString<List<ScenarioSeed>>(raw)
        val entities = seeds.map { s ->
            ScenarioEntity(
                id = s.id,
                theme = s.theme,
                context = s.context,
                role = s.role,
                powerDynamic = s.powerDynamic,
                stakesLevel = s.stakesLevel,
                tensionAxis = s.tensionAxis,
                scenarioText = s.scenarioText,
                optionLeftText = s.optionLeftText,
                optionRightText = s.optionRightText,
                optionLeftPosture = s.optionLeftPosture,
                optionRightPosture = s.optionRightPosture,
                avoidanceIndicator = s.avoidanceIndicator,
                primarySignalTracked = s.primarySignalTracked,
            )
        }
        scenarioDao.upsertAll(entities)
    }
}


