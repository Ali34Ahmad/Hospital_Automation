package com.example.signup.presentation

import com.example.model.enums.Gender


fun mockNavigationAction() = object : SignUpNavigationUiActions {
    override fun navigateToEmailVerificationScreen() {

    }

    override fun navigateToLoginScreen() {

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

    override fun onUpdatePasswordVisibilityChange(isVisible: Boolean) {

    }

    override fun onConfirmPasswordChange(confirmPassword: String) {

    }

    override fun onUpdateConfirmPasswordVisibilityChange(isVisible: Boolean) {

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