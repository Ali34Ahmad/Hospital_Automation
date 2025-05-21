package com.example.enter_email

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.request.SendOtpRequest
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnterEmailViewModel(
    private val authService: AuthApiService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EnterEmailUiState())
    val uiState = _uiState.asStateFlow()

    fun getUiActions(
        navActions: EnterEmailNavigationUiActions,
    ): EnterEmailUiActions = EnterEmailUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): EnterEmailBusinessUiActions = object : EnterEmailBusinessUiActions {
        override fun onEmailChange(email: String) {
            updateEmailText(email)
        }

        override fun onShowErrorDialogStateChange(value: Boolean) {
            updateShowErrorDialogState(value)
        }

        override fun onSendOtpCodeButtonClick() {
            sendOtpCode()
        }
    }

    private fun updateEmailText(value: String) {
        _uiState.update {
            it.copy(
                email = value
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

    private fun updateIsOtpCodeSentState(isSent: Boolean) {
        _uiState.update { it.copy(isSuccessful = isSent) }
    }

    private fun sendOtpCode() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Sending otp code","EnterEmailViewModel")
            val result = authService.sendOtpCodeToEmail(
                sendOtpCodeRequest = SendOtpRequest(
                    email = uiState.value.email,
                )
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Otp code sent successfully","EnterEmailViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                    updateIsOtpCodeSentState(true)
                }

                is Result.Error -> {
                    Log.v("Failed to send otp code","EnterEmailViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                    updateIsOtpCodeSentState(false)
                }
            }
        }
    }
}

