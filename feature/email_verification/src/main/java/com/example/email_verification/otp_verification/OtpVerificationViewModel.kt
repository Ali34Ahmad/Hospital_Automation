package com.example.email_verification.otp_verification

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.email_verification.EmailVerificationNavConstants
import com.example.network.model.request.SendOtpRequest
import com.example.network.model.request.VerifyEmailOtpRequest
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OtpVerificationViewModel(
    private val authService: AuthApiService,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(OtpVerificationUiState())
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

            override fun onOtpSentInitiallyChange(value: Boolean) {
                updateOtpSentInitially(value)
            }

            override fun onOtpCodeChange(index: Int, value: Int?) {
                updateOtpCodeText(index, value)
            }

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onSubmitButtonClick() {
                verifyOtpCode()
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

    private fun updateOtpSentInitially(value: Boolean) {
        _uiState.update {
            it.copy(
                otpCodeSentInitially = value
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
    }

    private fun updateIsLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateLoadingDialogText(text: String) {
        _uiState.update { it.copy(loadingDialogText = text) }
    }

    private fun updateErrorDialogText(text: String) {
        _uiState.update { it.copy(errorDialogText = text) }
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

    private fun verifyOtpCode() {
        viewModelScope.launch {
            updateLoadingDialogText("Verifying otp code")
            updateIsLoadingState(true)
            Log.v("Submitting otp verification info", "EmailVerificationViewModel")
            val result = authService.verifyEmail(
                verifyEmailOtpRequest = VerifyEmailOtpRequest(
                    email = uiState.value.email,
                    otp = uiState.value.otpCode.joinToString(separator = "")
                )
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Successful otp verification", "EmailVerificationViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                    updateIsSuccessfulState(true)
                }

                is Result.Error -> {
                    Log.v("Failed otp verification", "EmailVerificationViewModel")
                    updateIsLoadingState(false)
                    updateErrorDialogText("Failed to verify your email")
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                    updateIsSuccessfulState(false)
                }
            }
        }
    }

    private suspend fun sendOtpService() =
        authService.sendOtpCodeToEmail(
            sendOtpCodeRequest = SendOtpRequest(
                email = uiState.value.email,
            )
        )


    private fun resendOtpCode() {
        viewModelScope.launch {
            updateLoadingDialogText("Resending otp code")
            updateIsLoadingState(true)
            Log.v("Resending otp code", "EmailVerificationViewModel")
            val result = sendOtpService()
            updateIsLoadingState(false)
            when (result) {
                is Result.Success -> {
                    Log.v("Otp sent Successfully", "EmailVerificationViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                }

                is Result.Error -> {
                    Log.v("Sending otp failed", "EmailVerificationViewModel")
                    updateErrorDialogText("Failed to resend otp code")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                }
            }
        }
    }

//    private fun sendInitialOtp() {
//        viewModelScope.launch {
//            updateIsLoadingState(true)
//            Log.v("Sending otp code", "EmailVerificationViewModel")
//            sendOtpService()
//            updateOtpSentInitially(true)
//            updateIsLoadingState(false)
//        }
//    }

}

