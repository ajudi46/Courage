package com.courage.app.data.scenario

import com.courage.app.domain.model.Scenario

interface ScenarioRepository {
    suspend fun getNextScenario(): Scenario
    suspend fun getScenarioById(id: String): Scenario?
}


