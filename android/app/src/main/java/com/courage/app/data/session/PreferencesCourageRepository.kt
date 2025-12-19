package com.courage.app.data.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate

class PreferencesCourageRepository(
    private val dataStore: DataStore<Preferences>,
) : CourageRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _courageState = MutableStateFlow(
        CourageState(
            current = 5,
            lastResetDate = LocalDate.now(),
            lastDecayAtEpochMs = null,
        ),
    )
    override val courageState: StateFlow<CourageState> = _courageState

    init {
        scope.launch {
            dataStore.data.collect { prefs ->
                val date = runCatching { LocalDate.parse(prefs[Keys.LastResetDate]) }.getOrNull()
                    ?: LocalDate.now()
                val current = prefs[Keys.CurrentCourage] ?: 5
                val lastDecay = prefs[Keys.LastDecayAtEpochMs]
                _courageState.value = CourageState(
                    current = current.coerceIn(0, 5),
                    lastResetDate = date,
                    lastDecayAtEpochMs = lastDecay,
                )
            }
        }
    }

    override suspend fun ensureDailyReset(today: LocalDate) {
        val prefs = dataStore.data.first()
        val storedDate = runCatching { LocalDate.parse(prefs[Keys.LastResetDate]) }.getOrNull()
        if (storedDate == today) return

        dataStore.edit { p ->
            p[Keys.LastResetDate] = today.toString()
            p[Keys.CurrentCourage] = 5
            p.remove(Keys.LastDecayAtEpochMs)
        }
    }

    override suspend fun decrementOnceIfAllowed(nowEpochMs: Long, cooldownMs: Long): Boolean {
        var didDecrement = false
        dataStore.edit { p ->
            val current = (p[Keys.CurrentCourage] ?: 5).coerceIn(0, 5)
            val lastDecay = p[Keys.LastDecayAtEpochMs]
            val allowedByCooldown = lastDecay == null || (nowEpochMs - lastDecay) >= cooldownMs
            if (current > 0 && allowedByCooldown) {
                p[Keys.CurrentCourage] = (current - 1).coerceAtLeast(0)
                p[Keys.LastDecayAtEpochMs] = nowEpochMs
                didDecrement = true
            }
        }
        return didDecrement
    }

    private object Keys {
        val CurrentCourage = intPreferencesKey("courage_current")
        val LastResetDate = stringPreferencesKey("courage_last_reset_date")
        val LastDecayAtEpochMs = longPreferencesKey("courage_last_decay_at_ms")
    }
}


