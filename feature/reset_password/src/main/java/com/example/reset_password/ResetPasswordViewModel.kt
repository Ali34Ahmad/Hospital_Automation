package com.example.reset_password

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ResetPasswordNavConstants
import com.example.network.model.request.LoginRequest
import com.example.network.model.request.ResetPasswordRequest
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val authService: AuthApiService,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ResetPasswordUiState())
    val uiState = _uiState.asStateFlow()


    init {
        _uiState.update {
            it.copy(
                email = savedStateHandle.get<String>(ResetPasswordNavConstants.EMAIL) ?: "",
            )
        }
    }

    fun getUiActions(
        navActions: ResetPasswordNavigationUiActions,
    ): ResetPasswordUiActions = ResetPasswordUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): ResetPasswordBusinessUiActions =
        object : ResetPasswordBusinessUiActions {
            override fun onPasswordChange(password: String) {
                updatePasswordText(password)
            }

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onResetPasswordButtonClick() {
                resetPassword()
            }
        }

    private fun updatePasswordText(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
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

    private fun updateIsSuccessfulState(isSent: Boolean) {
        _uiState.update { it.copy(isSuccessful = isSent) }
    }

    private fun resetPassword() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Submitting new password", "ResetPasswordViewModel")
            val result = authService.resetPassword(
                resetPasswordRequest = ResetPasswordRequest(
                    email = uiState.value.email,
                    password = uiState.value.password,
                )
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Successful Reset Password", "ResetPasswordViewModel")
                    login()
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                    updateIsSuccessfulState(true)
                }

                is Result.Error -> {
                    Log.v("Failed to Reset Password", "ResetPasswordViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                    updateIsSuccessfulState(false)
                }
            }
        }
    }

    private suspend fun login() = authService.login(
        loginRequest = LoginRequest(
            email = uiState.value.email,
            password = uiState.value.password,
        )
    )
}

