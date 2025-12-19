package com.courage.app.data.onboarding

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PreferencesOnboardingRepository(
    private val dataStore: DataStore<Preferences>,
) : OnboardingRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _onboardingComplete = MutableStateFlow(false)
    override val onboardingComplete: StateFlow<Boolean> = _onboardingComplete

    init {
        scope.launch {
            dataStore.data
                .map { prefs -> prefs[Keys.OnboardingComplete] ?: false }
                .collect { _onboardingComplete.value = it }
        }
    }

    override suspend fun getProfileOrNull(): UserProfile? {
        val prefs = dataStore.data.first()
        val complete = prefs[Keys.OnboardingComplete] ?: false
        if (!complete) return null
        return UserProfile(
            // We intentionally only retain context across sessions.
            // Other onboarding inputs are collected once for relevance framing, but not persisted locally.
            ageRange = "",
            occupation = "",
            role = "",
            experienceLevel = "",
            preferredContext = prefs[Keys.PreferredContext] ?: "",
        )
    }

    override suspend fun setProfile(profile: UserProfile) {
        dataStore.edit { prefs ->
            prefs[Keys.PreferredContext] = profile.preferredContext
            prefs[Keys.OnboardingComplete] = true

            // Ensure we don't retain other fields from older builds.
            prefs.remove(Keys.AgeRange)
            prefs.remove(Keys.Occupation)
            prefs.remove(Keys.Role)
            prefs.remove(Keys.ExperienceLevel)
        }
    }

    private object Keys {
        val OnboardingComplete = booleanPreferencesKey("onboarding_complete")
        val AgeRange = stringPreferencesKey("profile_age_range")
        val Occupation = stringPreferencesKey("profile_occupation")
        val Role = stringPreferencesKey("profile_role")
        val ExperienceLevel = stringPreferencesKey("profile_experience_level")
        val PreferredContext = stringPreferencesKey("profile_preferred_context")
    }
}


