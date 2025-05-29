package com.example.signup.validator

import com.example.util.UiText

data class SignUpValidationResult(
    val firstNameError: UiText? = null,
    val middleNameError: UiText? = null,
    val lastNameError: UiText? = null,
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
            emailError,
            phoneNumberError,
            passwordError,
            confirmPasswordError
        ).isNotEmpty()
    }
}