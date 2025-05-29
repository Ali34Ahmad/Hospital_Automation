package com.example.utility.validation.validator

import com.example.utility.validation.ValidatorErrorMessage

interface Validator {
    fun validate(value: String): ValidatorErrorMessage?
}

