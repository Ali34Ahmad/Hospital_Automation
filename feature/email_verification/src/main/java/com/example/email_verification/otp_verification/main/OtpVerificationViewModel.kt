package com.example.email_verification.otp_verification.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.auth.SendOtpToEmailUseCase
import com.example.domain.use_cases.auth.VerifyEmailUseCase
import com.example.email_verification.EmailVerificationNavConstants
import com.example.email_verification.otp_verification.validator.OtpVerificationValidator
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.verify_otp.VerifyEmailOtpRequest
import com.example.model.enums.ScreenState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OtpVerificationViewModel(
    private val verifyOtpUseCase: VerifyEmailUseCase,
    private val sendOtpToEmailUseCase: SendOtpToEmailUseCase,
    private val otpVerificationValidator: OtpVerificationValidator,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(OtpVerificationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                email = savedStateHandle.get<String>(EmailVerificationNavConstants.EMAIL) ?: "",
                password = savedStateHandle.get<String>(EmailVerificationNavConstants.PASSWORD)
                    ?: "",
                navigateToResetPassword = savedStateHandle.get<Boolean>(
                    EmailVerificationNavConstants.NAVIGATE_TO_RESET_PASSWORD
                ) ?: false,
            )
        }
    }

    fun getUiActions(
        navActions: OtpVerificationNavigationUiActions,
    ): OtpVerificationUiActions = OtpVerificationUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): OtpVerificationBusinessUiActions =
        object : OtpVerificationBusinessUiActions {
            override fun onEmailTextChange(value: String) {
                updateEmailText(value)
            }

            override fun onOtpCodeChange(index: Int, value: Int?) {
                updateOtpCodeText(index, value)
            }

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onSubmitButtonClick() {
                onVerifyOtpClicked()
            }

            override fun onResendOtpVerificationCode() {
                resendOtpCode()
            }
        }

    private fun updateEmailText(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
    }

    private fun updateOtpCodeText(index: Int, value: Int?) {
        val newOtp = uiState.value.otpCode.toMutableList()
        newOtp[index] = value
        _uiState.update {
            it.copy(
                otpCode = newOtp
            )
        }
        updateLoginButtonEnablement()
    }

    private fun updateLoadingDialogText(text: String) {
        _uiState.update { it.copy(loadingDialogText = text) }
    }

    private fun updateErrorDialogText(text: UiText) {
        _uiState.update { it.copy(errorDialogText = text) }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateLoginButtonEnablement() {
        _uiState.update { currentState ->
            val allFieldsFilled = currentState.otpCode.all { it != null }

            currentState.copy(isVerifyButtonEnabled = allFieldsFilled)
        }
    }

    fun onVerifyOtpClicked() {
        val validationResult = otpVerificationValidator.validate(uiState.value)
        _uiState.update {
            it.copy(
                otpCodeError = validationResult.otpError
            )
        }

        if (!validationResult.hasErrors()) {
            verifyOtpCode()
        }
    }

    private fun updateScreenState(screenState: ScreenState){
        _uiState.update {
            it.copy(
                screenState=screenState
            )
        }
    }
    private fun verifyOtpCode() {
        viewModelScope.launch {
            updateLoadingDialogText("Verifying otp code")
            updateScreenState(ScreenState.LOADING)
            Log.v("Submitting otp verification info", "EmailVerificationViewModel")
            verifyOtpUseCase(
                verifyEmailOtpRequest = VerifyEmailOtpRequest(
                    email = uiState.value.email.trim(),
                    otp = uiState.value.otpCode.joinToString(separator = "").trim(),
                )
            ).onSuccess{
                Log.v("Successful otp verification", "EmailVerificationViewModel")
                updateScreenState(ScreenState.SUCCESS)
                updateShowErrorDialogState(false)
            }.onError {error->
                Log.v("Failed otp verification", "EmailVerificationViewModel")
                updateScreenState(ScreenState.ERROR)
                updateErrorDialogText(UiText.StringResource(R.string.failed_to_verify_your_email))
                updateShowErrorDialogState(true)
            }
        }
    }

    private suspend fun sendOtpService() =
        sendOtpToEmailUseCase(
            sendOtpCodeRequest = SendOtpRequest(
                email = uiState.value.email,
            )
        )


    private fun resendOtpCode() {
        viewModelScope.launch {
            updateLoadingDialogText("Resending otp code")
            updateScreenState(ScreenState.LOADING)
            Log.v("Resending otp code", "EmailVerificationViewModel")
            sendOtpService()
                .onSuccess {
                    Log.v("Otp sent Successfully", "EmailVerificationViewModel")
                    updateScreenState(ScreenState.SUCCESS)
                    updateShowErrorDialogState(false)
                }.onError {error->
                    Log.v("Sending otp failed", "EmailVerificationViewModel")
                    updateErrorDialogText(UiText.StringResource(R.string.failed_to_resend_otp_code))
                    updateScreenState(ScreenState.ERROR)
                    updateShowErrorDialogState(true)
                }
        }
    }
}

