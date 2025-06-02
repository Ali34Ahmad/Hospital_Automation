package com.example.email_verification.email_verified_successfully.main

class EmailVerifiedSuccessfullyUiActions(
    navigationActions:EmailVerifiedSuccessfullyNavigationUiActions,
    businessActions:EmailVerifiedSuccessfullyBusinessUiActions,
) :EmailVerifiedSuccessfullyBusinessUiActions by businessActions,
   EmailVerifiedSuccessfullyNavigationUiActions by navigationActions


interface EmailVerifiedSuccessfullyBusinessUiActions {
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onNextButtonClick()
}

interface EmailVerifiedSuccessfullyNavigationUiActions {
    fun navigateToUploadEmployeeDocumentsScreen()
    fun navigateToResetPasswordScreen()
}