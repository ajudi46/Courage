package com.courage.app.data.remote

import com.courage.app.data.onboarding.UserProfile

data class RemoteUserProfile(
    val uid: String,
    val preferences: UserProfile,
)

interface UserProfileRemoteRepository {
    suspend fun getOrNull(uid: String): RemoteUserProfile?
    suspend fun upsert(uid: String, preferences: UserProfile)
    suspend fun touchLastUsed(uid: String)
}


