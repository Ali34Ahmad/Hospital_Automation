package com.example.email_verification.otp_verification


fun mockEmailVerificationNavigationUiActions()=object : OtpVerificationNavigationUiActions {
    override fun navigateToEmailVerifiedSuccessfullyScreen() {

    }

}

fun mockEmailVerificationBusinessUiActions()=object : OtpVerificationBusinessUiActions {
    override fun onEmailTextChange(value: String) {

    }

    override fun onOtpSentInitiallyChange(value: Boolean) {

    }

    override fun onOtpCodeChange(index: Int, value: Int?) {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onSubmitButtonClick() {

    }

    override fun onResendOtpVerificationCode() {

    }

}
