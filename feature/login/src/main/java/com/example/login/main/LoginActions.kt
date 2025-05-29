package com.example.login.main

class LoginUiActions(
    navigationActions:LoginNavigationUiActions,
    businessActions:LoginBusinessUiActions,
) :LoginBusinessUiActions by businessActions,
   LoginNavigationUiActions by navigationActions


interface LoginBusinessUiActions {
    fun onEmailChange(email: String)
    fun onPasswordChange(password: String)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onLoginButtonClick()
}

interface LoginNavigationUiActions {
    fun navigateToSignUpScreen()
    fun navigateToEnterEmailScreen()
    fun navigateToHomeScreen()
}