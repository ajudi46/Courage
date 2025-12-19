package com.courage.app.data.decision

import com.courage.app.domain.model.DecisionEvent

interface DecisionRepository {
    suspend fun recordDecision(event: DecisionEvent): Long
    suspend fun getRecent(limit: Int): List<DecisionEvent>
    suspend fun getBetween(startInclusiveEpochMs: Long, endExclusiveEpochMs: Long): List<DecisionEvent>
    suspend fun getById(id: Long): DecisionEvent?
}


