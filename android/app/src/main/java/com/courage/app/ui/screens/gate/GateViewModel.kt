package com.courage.app.ui.screens.gate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courage.app.data.auth.AuthRepository
import com.courage.app.data.onboarding.OnboardingRepository
import com.courage.app.data.remote.UserProfileRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GateViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val onboardingRepository: OnboardingRepository,
    private val userProfileRemoteRepository: UserProfileRemoteRepository,
) : ViewModel() {

    enum class GateState { Loading, NeedAuth, NeedOnboarding, Ready }

    private val _gateState = MutableStateFlow(GateState.Loading)
    val gateState: StateFlow<GateState> = _gateState

    init {
        viewModelScope.launch {
            authRepository.currentUser.collect { user ->
                if (user == null) {
                    _gateState.value = GateState.NeedAuth
                    return@collect
                }

                // Remote-first: if the profile exists in Firestore, we don't ask preferences again.
                _gateState.value = GateState.Loading
                val remote = runCatching { userProfileRemoteRepository.getOrNull(user.uid) }.getOrNull()
                if (remote != null) {
                    // Cache minimal local state for offline-friendly scenario selection.
                    onboardingRepository.setProfile(remote.preferences)
                    // Touch last used timestamp (best effort).
                    runCatching { userProfileRemoteRepository.touchLastUsed(user.uid) }
                    _gateState.value = GateState.Ready
                } else {
                    _gateState.value = GateState.NeedOnboarding
                }
            }
        }
    }
}


