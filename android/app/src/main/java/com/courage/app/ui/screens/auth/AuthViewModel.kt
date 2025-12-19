package com.courage.app.ui.screens.auth

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courage.app.data.auth.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    val currentUser = authRepository.currentUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getGoogleIntent(context: Context): Intent = authRepository.getGoogleSignInIntent(context)

    fun onGoogleSignInResult(data: Intent?) {
        viewModelScope.launch {
            runCatching {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                val idToken = account.idToken ?: error("Missing ID token")
                authRepository.signInWithGoogleIdToken(idToken)
            }.onFailure { e ->
                _error.value = e.message ?: "Sign-in failed"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}


