package com.courage.app.data.onboarding

import kotlinx.coroutines.flow.StateFlow

data class UserProfile(
    val ageRange: String,
    val occupation: String,
    val role: String,
    val experienceLevel: String,
    val preferredContext: String,
)

interface OnboardingRepository {
    val onboardingComplete: StateFlow<Boolean>
    suspend fun getProfileOrNull(): UserProfile?
    suspend fun setProfile(profile: UserProfile)
}


