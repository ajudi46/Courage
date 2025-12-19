package com.courage.app.data.auth

import android.content.Context
import android.content.Intent
import com.courage.app.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {

    private val _currentUser = MutableStateFlow<AuthUser?>(
        firebaseAuth.currentUser?.let {
            AuthUser(uid = it.uid, email = it.email, displayName = it.displayName)
        },
    )

    override val currentUser: StateFlow<AuthUser?> = _currentUser

    init {
        firebaseAuth.addAuthStateListener { auth ->
            val user = auth.currentUser
            _currentUser.tryEmit(
                user?.let { AuthUser(uid = it.uid, email = it.email, displayName = it.displayName) },
            )
        }
    }

    override fun getGoogleSignInIntent(context: Context): Intent {
        val webClientId = context.getString(R.string.default_web_client_id)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso).signInIntent
    }

    override suspend fun signInWithGoogleIdToken(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).await()
    }

    override suspend fun signOut(context: Context) {
        firebaseAuth.signOut()
        // Also sign out of the Google client so next login is explicit.
        val webClientId = context.getString(R.string.default_web_client_id)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso).signOut().await()
    }
}


