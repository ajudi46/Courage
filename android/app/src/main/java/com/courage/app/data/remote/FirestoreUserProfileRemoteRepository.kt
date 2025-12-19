package com.courage.app.data.remote

import com.courage.app.data.onboarding.UserProfile
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class FirestoreUserProfileRemoteRepository(
    private val firestore: FirebaseFirestore,
) : UserProfileRemoteRepository {

    override suspend fun getOrNull(uid: String): RemoteUserProfile? {
        val snapshot = firestore.collection(FirestorePaths.Users).document(uid).get().await()
        if (!snapshot.exists()) return null

        val prefs = snapshot.get("preferences") as? Map<*, *> ?: emptyMap<Any, Any>()
        return RemoteUserProfile(
            uid = uid,
            preferences = UserProfile(
                ageRange = prefs["ageRange"] as? String ?: "",
                occupation = prefs["occupationIndustry"] as? String ?: "",
                role = prefs["role"] as? String ?: "",
                experienceLevel = prefs["experienceLevel"] as? String ?: "",
                preferredContext = prefs["preferredContext"] as? String ?: "",
            ),
        )
    }

    override suspend fun upsert(uid: String, preferences: UserProfile) {
        val doc = firestore.collection(FirestorePaths.Users).document(uid)
        val payload = mapOf(
            "uid" to uid,
            "createdAt" to FieldValue.serverTimestamp(),
            "lastUsedAt" to FieldValue.serverTimestamp(),
            "preferences" to mapOf(
                "ageRange" to preferences.ageRange,
                "occupationIndustry" to preferences.occupation,
                "role" to preferences.role,
                "experienceLevel" to preferences.experienceLevel,
                "preferredContext" to preferences.preferredContext,
            ),
        )
        doc.set(payload, SetOptions.merge()).await()
    }

    override suspend fun touchLastUsed(uid: String) {
        firestore.collection(FirestorePaths.Users).document(uid)
            .set(mapOf("lastUsedAt" to FieldValue.serverTimestamp()), SetOptions.merge())
            .await()
    }
}


