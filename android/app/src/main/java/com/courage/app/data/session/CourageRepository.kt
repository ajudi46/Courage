package com.courage.app.data.session

import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

data class CourageState(
    val current: Int,
    val lastResetDate: LocalDate,
    val lastDecayAtEpochMs: Long?,
)

interface CourageRepository {
    val courageState: StateFlow<CourageState>

    suspend fun ensureDailyReset(today: LocalDate = LocalDate.now())
    suspend fun decrementOnceIfAllowed(nowEpochMs: Long, cooldownMs: Long): Boolean
}


