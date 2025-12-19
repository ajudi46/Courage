package com.courage.app.data.decision

import com.courage.app.data.db.dao.DecisionDao
import com.courage.app.data.db.entities.DecisionEntity
import com.courage.app.domain.model.ChoiceSide
import com.courage.app.domain.model.DecisionEvent
import com.courage.app.domain.model.DecisionPosture
import com.courage.app.domain.model.DecisionTheme

class RoomDecisionRepository(
    private val decisionDao: DecisionDao,
) : DecisionRepository {
    override suspend fun recordDecision(event: DecisionEvent): Long {
        return decisionDao.insert(
            DecisionEntity(
                scenarioId = event.scenarioId,
                theme = event.theme.name,
                chosenSide = event.chosenSide.name,
                chosenPosture = event.chosenPosture.name,
                timeToDecisionMs = event.timeToDecisionMs,
                isAvoidantChoice = event.isAvoidantChoice,
                createdAtEpochMs = event.createdAtEpochMs,
            ),
        )
    }

    override suspend fun getRecent(limit: Int): List<DecisionEvent> =
        decisionDao.getRecent(limit).map { it.toDomain() }

    override suspend fun getBetween(
        startInclusiveEpochMs: Long,
        endExclusiveEpochMs: Long,
    ): List<DecisionEvent> {
        return decisionDao.getBetween(
            startInclusiveEpochMs = startInclusiveEpochMs,
            endExclusiveEpochMs = endExclusiveEpochMs,
        ).map { it.toDomain() }
    }

    override suspend fun getById(id: Long): DecisionEvent? = decisionDao.getById(id)?.toDomain()
}

private fun DecisionEntity.toDomain(): DecisionEvent {
    return DecisionEvent(
        scenarioId = scenarioId,
        theme = DecisionTheme.valueOf(theme),
        chosenSide = ChoiceSide.valueOf(chosenSide),
        chosenPosture = DecisionPosture.valueOf(chosenPosture),
        timeToDecisionMs = timeToDecisionMs,
        isAvoidantChoice = isAvoidantChoice,
        createdAtEpochMs = createdAtEpochMs,
    )
}


