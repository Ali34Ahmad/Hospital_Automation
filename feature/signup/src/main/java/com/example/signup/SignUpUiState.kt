package com.example.signup

import com.example.constants.enums.Gender
import com.example.utility.network.Error

data class SignUpUiState(
    val firstName: String = "",
    val firstNameError: String? = null,
    val middleName: String = "",
    val middleNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val gender: Gender? = null,
    val genderError: String? = null,
    val isTermsAndConditionsChecked: Boolean = false,
    val isPersonalDataAccessibilityChecked: Boolean = false,
    val isSignUpButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessful: Boolean = false,
)