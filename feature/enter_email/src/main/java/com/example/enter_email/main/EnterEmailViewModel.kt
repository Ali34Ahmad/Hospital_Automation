package com.example.enter_email.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.auth.SendOtpToEmailUseCase
import com.example.enter_email.validator.EnterEmailValidationResult
import com.example.enter_email.validator.EnterEmailValidator
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.utility.network.Error
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnterEmailViewModel(
    private val sendOtpCodeToEmailUseCase: SendOtpToEmailUseCase,
    private val enterEmailValidator: EnterEmailValidator,
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
            validateAndSendOtpCode()
        }
    }

    private fun updateEmailText(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
        updateSendOtpButtonEnablement()
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

    private fun updateSendOtpButtonEnablement() {
        _uiState.update { currentState ->
            val allFieldsFilled = currentState.email.isNotEmpty()

            currentState.copy(isSendOtpCodeButtonEnabled = allFieldsFilled)
        }
    }

    private fun updateValidationErrors(result: EnterEmailValidationResult) {
        _uiState.update {
            it.copy(
                emailError = result.emailError,
            )
        }
    }

    private fun sendOtpCode() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Sending otp code","EnterEmailViewModel")
            sendOtpCodeToEmailUseCase(
                sendOtpCodeRequest = SendOtpRequest(
                    email = uiState.value.email,
                )
            ).onSuccess{
                Log.v("Otp code sent successfully","EnterEmailViewModel")
                updateIsLoadingState(false)
                updateShowErrorDialogState(false)
                updateErrorState(null)
                updateIsOtpCodeSentState(true)
            }.onError {error->
                Log.v("Failed to send otp code","EnterEmailViewModel")
                updateIsLoadingState(false)
                updateShowErrorDialogState(true)
                updateErrorState(error)
                updateIsOtpCodeSentState(false)
            }
        }
    }

    private fun validateAndSendOtpCode(){
        val validationResult = enterEmailValidator.validate(uiState.value)
        updateValidationErrors(validationResult)
        if (!validationResult.hasErrors()) {
            sendOtpCode()
        }
    }
}