package com.example.reset_password


fun mockResetPasswordNavigationUiActions()=object : ResetPasswordNavigationUiActions {
    override fun navigateToHomeScreen() {

    }

}

fun mockResetPasswordBusinessUiActions()=object : ResetPasswordBusinessUiActions {
    override fun onPasswordChange(password: String) {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onResetPasswordButtonClick() {

    }
}
