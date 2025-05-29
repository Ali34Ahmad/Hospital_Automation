package com.example.enter_email.main

class EnterEmailUiActions(
    navigationActions:EnterEmailNavigationUiActions,
    businessActions:EnterEmailBusinessUiActions,
) :EnterEmailBusinessUiActions by businessActions,
   EnterEmailNavigationUiActions by navigationActions


interface EnterEmailBusinessUiActions {
    fun onEmailChange(email: String)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onSendOtpCodeButtonClick()
}

interface EnterEmailNavigationUiActions {
    fun navigateToEmailOtpVerificationScreen()
}