package com.example.email_verification.otp_verification.main

import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.Error

data class OtpVerificationUiState(
    val email: String = "",
    val password: String = "",
    val navigateToResetPassword: Boolean = false,
    val otpCode: List<Int?> = listOf (null,null,null,null,),
    val otpCodeError: UiText? = null,
    val isVerifyButtonEnabled: Boolean = false,
    val loadingDialogText:String="",
    val showErrorDialog: Boolean = false,
    val errorDialogText: UiText?= null,
    val screenState: ScreenState= ScreenState.IDLE,
)