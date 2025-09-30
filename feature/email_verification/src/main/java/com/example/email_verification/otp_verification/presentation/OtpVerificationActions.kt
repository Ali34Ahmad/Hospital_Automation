package com.example.email_verification.otp_verification.presentation

class OtpVerificationUiActions(
    navigationActions:OtpVerificationNavigationUiActions,
    businessActions:OtpVerificationBusinessUiActions,
) :OtpVerificationBusinessUiActions by businessActions,
   OtpVerificationNavigationUiActions by navigationActions


interface OtpVerificationBusinessUiActions {
    fun onEmailTextChange(value: String)
    fun onOtpCodeChange(index: Int, value: Int?)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onSubmitButtonClick()
    fun onResendOtpVerificationCode()
}

interface OtpVerificationNavigationUiActions {
    fun navigateToEmailVerifiedSuccessfullyScreen()
}