package com.example.utility.validation.validator

import com.example.utility.validation.ValidatorErrorMessage

data object PositiveNumberValidator : Validator {
    override fun validate(value: String): ValidatorErrorMessage.PositiveNumber? {


        if (value.isBlank()) {
            return ValidatorErrorMessage.PositiveNumber.EMPTY_FIELD
        }

        val number = value.toIntOrNull()
        return when {
            number == null -> ValidatorErrorMessage.PositiveNumber.INVALID_NUMBER
            number <= 0 -> ValidatorErrorMessage.PositiveNumber.NEGATIVE_NUMBER
            else -> null
        }
    }
}