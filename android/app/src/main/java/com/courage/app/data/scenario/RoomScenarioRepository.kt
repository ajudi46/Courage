package com.courage.app.data.scenario

import android.content.Context
import com.courage.app.data.db.dao.DecisionDao
import com.courage.app.data.db.dao.ScenarioDao
import com.courage.app.data.onboarding.OnboardingRepository
import com.courage.app.domain.model.ChoiceSide
import com.courage.app.domain.model.DecisionPosture
import com.courage.app.domain.model.DecisionTheme
import com.courage.app.domain.model.Scenario

class RoomScenarioRepository(
    private val context: Context,
    private val scenarioDao: ScenarioDao,
    private val decisionDao: DecisionDao,
    private val onboardingRepository: OnboardingRepository,
) : ScenarioRepository {

    private val seeder = ScenarioSeeder(context, scenarioDao)

    override suspend fun getNextScenario(): Scenario {
        seeder.seedIfNeeded()

        val lastDecisionTheme = decisionDao.getRecent(1).firstOrNull()?.theme
        val preferredContext = onboardingRepository.getProfileOrNull()?.preferredContext?.ifBlank { null }

        val entity = when {
            preferredContext != null && lastDecisionTheme != null ->
                scenarioDao.getRandomByContextExcludingTheme(preferredContext, lastDecisionTheme)
                    ?: scenarioDao.getRandomByContext(preferredContext)

            preferredContext != null ->
                scenarioDao.getRandomByContext(preferredContext)

            lastDecisionTheme != null ->
                scenarioDao.getRandomExcludingTheme(lastDecisionTheme)

            else -> null
        } ?: scenarioDao.getRandomAny()
            ?: error("No scenarios available after seeding.")

        return entity.toDomain()
    }

    override suspend fun getScenarioById(id: String): Scenario? {
        seeder.seedIfNeeded()
        return scenarioDao.getById(id)?.toDomain()
    }
}

private fun com.courage.app.data.db.entities.ScenarioEntity.toDomain(): Scenario {
    return Scenario(
        id = id,
        theme = DecisionTheme.valueOf(theme),
        context = context,
        role = role,
        powerDynamic = powerDynamic,
        stakesLevel = stakesLevel,
        tensionAxis = tensionAxis,
        scenarioText = scenarioText,
        optionLeftText = optionLeftText,
        optionRightText = optionRightText,
        optionLeftPosture = DecisionPosture.valueOf(optionLeftPosture),
        optionRightPosture = DecisionPosture.valueOf(optionRightPosture),
        avoidanceIndicator = ChoiceSide.valueOf(avoidanceIndicator),
        primarySignalTracked = primarySignalTracked,
    )
}


