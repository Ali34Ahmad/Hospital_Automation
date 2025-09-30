package com.example.doctor_signup.presentation.validator

import com.example.util.UiText

data class DoctorSignUpValidationResult(
    val firstNameError: UiText? = null,
    val middleNameError: UiText? = null,
    val lastNameError: UiText? = null,
    val specialtyError: UiText? = null,
    val emailError: UiText? = null,
    val phoneNumberError: UiText? = null,
    val passwordError: UiText? = null,
    val confirmPasswordError: UiText? = null
) {
    fun hasErrors(): Boolean {
        return listOfNotNull(
            firstNameError,
            middleNameError,
            lastNameError,
            specialtyError,
            emailError,
            phoneNumberError,
            passwordError,
            confirmPasswordError
        ).isNotEmpty()
    }
}