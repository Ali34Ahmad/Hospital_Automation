package com.example.enter_email


fun mockEnterEmailNavigationUiActions()=object : EnterEmailNavigationUiActions {
    override fun navigateToEmailOtpVerificationScreen() {

    }

}

fun mockEnterEmailBusinessUiActions()=object : EnterEmailBusinessUiActions {
    override fun onEmailChange(email: String) {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onSendOtpCodeButtonClick() {

    }

}
