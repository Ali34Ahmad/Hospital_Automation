package com.example.utility.validation


data object TextValidator : Validator {
    override fun validate(value: String): ValidatorErrorMessage? =
        if(value.isBlank()){
            ValidatorErrorMessage.EMPTY_FIELD
        }else null
}