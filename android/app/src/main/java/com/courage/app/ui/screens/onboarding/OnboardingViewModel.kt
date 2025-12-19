package com.courage.app.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courage.app.data.auth.AuthRepository
import com.courage.app.data.onboarding.OnboardingRepository
import com.courage.app.data.onboarding.UserProfile
import com.courage.app.data.remote.UserProfileRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
    private val authRepository: AuthRepository,
    private val userProfileRemoteRepository: UserProfileRemoteRepository,
) : ViewModel() {

    data class UiState(
        val ageRange: String = "",
        val occupation: String = "",
        val role: String = "",
        val experienceLevel: String = "",
        val preferredContext: String = "",
        val isSaving: Boolean = false,
        val error: String? = null,
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun updateAgeRange(value: String) = _state.value.let { _state.value = it.copy(ageRange = value) }
    fun updateOccupation(value: String) = _state.value.let { _state.value = it.copy(occupation = value) }
    fun updateRole(value: String) = _state.value.let { _state.value = it.copy(role = value) }
    fun updateExperience(value: String) = _state.value.let { _state.value = it.copy(experienceLevel = value) }
    fun updatePreferredContext(value: String) = _state.value.let { _state.value = it.copy(preferredContext = value) }

    fun submit(onComplete: () -> Unit) {
        val s = _state.value
        if (
            s.ageRange.isBlank() ||
            s.occupation.isBlank() ||
            s.role.isBlank() ||
            s.experienceLevel.isBlank() ||
            s.preferredContext.isBlank()
        ) {
            _state.value = s.copy(error = "Please complete all fields.")
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isSaving = true, error = null)
            runCatching {
                val uid = authRepository.currentUser.value?.uid ?: error("Not signed in")
                onboardingRepository.setProfile(
                    UserProfile(
                        ageRange = s.ageRange,
                        occupation = s.occupation,
                        role = s.role,
                        experienceLevel = s.experienceLevel,
                        preferredContext = s.preferredContext.ifBlank { "Office" },
                    ),
                )
                // Persist full onboarding preferences remotely (source of truth).
                userProfileRemoteRepository.upsert(
                    uid = uid,
                    preferences = UserProfile(
                        ageRange = s.ageRange,
                        occupation = s.occupation,
                        role = s.role,
                        experienceLevel = s.experienceLevel,
                        preferredContext = s.preferredContext.ifBlank { "Office" },
                    ),
                )
            }.onSuccess {
                _state.value = _state.value.copy(isSaving = false)
                onComplete()
            }.onFailure { e ->
                _state.value = _state.value.copy(isSaving = false, error = e.message ?: "Could not save.")
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}


