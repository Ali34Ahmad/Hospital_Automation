package com.example.utility.validation.validator

import android.util.Patterns
import com.example.utility.validation.ValidatorErrorMessage

data object EmailValidator : Validator {
    override fun validate(value: String): ValidatorErrorMessage.Email? {
        if (value.isBlank()) {
            return ValidatorErrorMessage.Email.EMPTY_FIELD
        }

        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(value).matches()

        return if (isEmailValid) {
            null
        } else {
            ValidatorErrorMessage.Email.INVALID_EMAIL
        }
    }
}