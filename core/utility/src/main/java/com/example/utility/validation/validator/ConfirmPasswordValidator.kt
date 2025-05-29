package com.example.utility.validation.validator

import com.example.utility.validation.ValidatorErrorMessage

data object ConfirmPasswordValidator {
    fun validate(value: String,password: String): ValidatorErrorMessage.ConfirmPassword? {
        if (value.isBlank()) {
            return ValidatorErrorMessage.ConfirmPassword.EMPTY_FIELD
        }
        if (value != password) {
            return ValidatorErrorMessage.ConfirmPassword.DOES_NOT_MATCH_PASSWORD
        }
        return null
    }
}