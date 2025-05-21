package com.example.email_verification.otp_verification

import com.example.utility.network.Error

data class OtpVerificationUiState(
    val email: String = "",
    val password: String = "",
    val navigateToResetPassword: Boolean = false,
    val otpCodeSentInitially: Boolean = false,
    val otpCode: List<Int?> = listOf (null,null,null,null,),
    val otpCodeError: String? = null,
    val isSignUpButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val loadingDialogText:String="",
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val errorDialogText: String = "",
    val isSuccessful: Boolean = false,
)