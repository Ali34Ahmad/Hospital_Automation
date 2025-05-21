package com.example.signup

import com.example.constants.enums.Gender


fun mockNavigationAction() = object : SignUpNavigationUiActions {
    override fun navigateToEmailVerificationScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToLoginScreen() {
        TODO("Not yet implemented")
    }
}

fun mockBusinessUiActions() = object : SignUpBusinessUiActions {
    override fun onFirstNameChange(firstName: String) {
    }

    override fun onMiddleNameChange(middleName: String) {

    }

    override fun onLastNameChange(lastName: String) {

    }

    override fun onEmailChange(email: String) {

    }

    override fun onPhoneNumberChange(phoneNumber: String) {

    }

    override fun onPasswordChange(password: String) {

    }

    override fun onConfirmPasswordChange(confirmPassword: String) {

    }

    override fun onGenderChange(gender: Gender) {

    }

    override fun onTermsAndConditionsCheckChange(value: Boolean) {

    }

    override fun onPersonalDataAccessibilityCheckChange(value: Boolean) {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onSignUpButtonClick() {

    }
}