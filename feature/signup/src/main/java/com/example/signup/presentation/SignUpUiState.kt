package com.example.signup.presentation

import com.example.model.enums.ScreenState
import com.example.model.enums.Gender
import com.example.util.UiText

data class SignUpUiState(
    val firstName: String = "",
    val firstNameError: UiText? = null,
    val middleName: String = "",
    val middleNameError: UiText? = null,
    val lastName: String = "",
    val lastNameError: UiText? = null,
    val email: String = "",
    val emailError: UiText? = null,
    val phoneNumber: String = "",
    val phoneNumberError: UiText? = null,
    val password: String = "",
    val showPasswordText: Boolean = false,
    val passwordError: UiText? = null,
    val confirmPassword: String = "",
    val showConfirmPasswordText: Boolean = false,
    val confirmPasswordError: UiText? = null,
    val gender: Gender? = null,
    val genderError: String? = null,
    val isTermsAndConditionsChecked: Boolean = false,
    val isPersonalDataAccessibilityChecked: Boolean = false,
    val isSignUpButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState:ScreenState=ScreenState.IDLE,
)