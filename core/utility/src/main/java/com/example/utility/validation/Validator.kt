package com.example.utility.validation

interface Validator {
    fun validate(value: String): ValidatorErrorMessage?
}

