package com.example.email_verification.email_verified_successfully

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.email_verification.EmailVerificationNavConstants
import com.example.network.model.request.LoginRequest
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmailVerifiedSuccessfullyViewModel(
    private val authService: AuthApiService,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmailVerifiedSuccessfullyUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                email = savedStateHandle.get<String>(EmailVerificationNavConstants.EMAIL) ?: "",
                password = savedStateHandle.get<String>(EmailVerificationNavConstants.PASSWORD) ?: "",
                navigateToResetPassword = savedStateHandle.get<Boolean>(EmailVerificationNavConstants.NAVIGATE_TO_RESET_PASSWORD) ?: false,
            )
        }
    }

    fun getUiActions(
        navActions: EmailVerifiedSuccessfullyNavigationUiActions,
    ): EmailVerifiedSuccessfullyUiActions = EmailVerifiedSuccessfullyUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): EmailVerifiedSuccessfullyBusinessUiActions =
        object : EmailVerifiedSuccessfullyBusinessUiActions {

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onNextButtonClick() {
                login()
            }
        }

    private fun updateIsLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateErrorState(error: Error?) {
        _uiState.update { it.copy(error = error) }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateIsSuccessfulState(isSuccessful: Boolean) {
        _uiState.update { it.copy(isSuccessful = isSuccessful) }
    }

    private fun login() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Submitting log in info", "EmailVerifiedSuccessfullyViewModel")
            val result = authService.login(
                loginRequest = LoginRequest(
                    email = uiState.value.email,
                    password = uiState.value.password,
                )
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Successful log in", "EmailVerifiedSuccessfullyViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                    updateIsSuccessfulState(true)
                }

                is Result.Error -> {
                    Log.v("Failed log in", "EmailVerifiedSuccessfullyViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                    updateIsSuccessfulState(false)
                }
            }
        }
    }
}

