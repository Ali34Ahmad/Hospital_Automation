package com.example.utility.validation.validator

import com.example.utility.validation.ValidatorErrorMessage

data object PasswordValidator : Validator {
    override fun validate(value: String): ValidatorErrorMessage.Password? {
        if (value.isBlank()) {
            return ValidatorErrorMessage.Password.EMPTY_FIELD
        }
        if (value.length<8) {
            return ValidatorErrorMessage.Password.LESS_THAN_EIGHT_CHARS
        }
        return null
    }
}