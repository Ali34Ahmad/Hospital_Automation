package com.example.reset_password.main

class ResetPasswordUiActions(
    navigationActions:ResetPasswordNavigationUiActions,
    businessActions:ResetPasswordBusinessUiActions,
) :ResetPasswordBusinessUiActions by businessActions,
   ResetPasswordNavigationUiActions by navigationActions


interface ResetPasswordBusinessUiActions {
    fun onPasswordChange(password: String)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onResetPasswordButtonClick()
}

interface ResetPasswordNavigationUiActions {
    fun navigateToHomeScreen()
}