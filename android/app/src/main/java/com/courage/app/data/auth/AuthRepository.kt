package com.courage.app.data.auth

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.StateFlow

data class AuthUser(
    val uid: String,
    val email: String?,
    val displayName: String?,
)

interface AuthRepository {
    val currentUser: StateFlow<AuthUser?>

    fun getGoogleSignInIntent(context: Context): Intent
    suspend fun signInWithGoogleIdToken(idToken: String)
    suspend fun signOut(context: Context)
}


