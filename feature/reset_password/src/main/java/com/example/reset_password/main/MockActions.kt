package com.example.reset_password.main


fun mockResetPasswordNavigationUiActions()=object : ResetPasswordNavigationUiActions {
    override fun navigateToHomeScreen() {

    }

}

fun mockResetPasswordBusinessUiActions()=object : ResetPasswordBusinessUiActions {
    override fun onPasswordChange(password: String) {

    }

    override fun onUpdatePasswordVisibility(isVisible: Boolean) {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onResetPasswordButtonClick() {

    }
}
