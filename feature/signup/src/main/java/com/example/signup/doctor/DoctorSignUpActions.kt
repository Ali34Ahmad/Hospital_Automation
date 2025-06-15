package com.example.signup.doctor

import com.example.model.enums.Gender

class DoctorDoctorSignUpUiActions(
    navigationActions: DoctorSignUpNavigationUiActions,
    businessActions: SignUpBusinessUiActions,
) : SignUpBusinessUiActions by businessActions,
    DoctorSignUpNavigationUiActions by navigationActions


interface SignUpBusinessUiActions {
    fun onFirstNameChange(firstName: String)
    fun onMiddleNameChange(middleName: String)
    fun onLastNameChange(lastName: String)
    fun onSpecialtyChange(lastName: String)
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

interface DoctorSignUpNavigationUiActions {
    fun navigateToEmailVerificationScreen()
    fun navigateToLoginScreen()
}