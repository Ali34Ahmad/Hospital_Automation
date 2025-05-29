package com.example.signup.main

import com.example.model.enums.Gender

class SignUpUiActions(
    navigationActions: SignUpNavigationUiActions,
    businessActions: SignUpBusinessUiActions,
) : SignUpBusinessUiActions by businessActions,
    SignUpNavigationUiActions by navigationActions


interface SignUpBusinessUiActions {
    fun onFirstNameChange(firstName: String)
    fun onMiddleNameChange(middleName: String)
    fun onLastNameChange(lastName: String)
    fun onEmailChange(email: String)
    fun onPhoneNumberChange(phoneNumber: String)
    fun onPasswordChange(password: String)
    fun onUpdatePasswordVisibilityChange(isVisible: Boolean)
    fun onConfirmPasswordChange(confirmPassword: String)
    fun onUpdateConfirmPasswordVisibilityChange(isVisible: Boolean)
    fun onGenderChange(gender: Gender)
    fun onTermsAndConditionsCheckChange(value: Boolean)
    fun onPersonalDataAccessibilityCheckChange(value: Boolean)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onSignUpButtonClick()
}

interface SignUpNavigationUiActions {
    fun navigateToEmailVerificationScreen()
    fun navigateToLoginScreen()
}