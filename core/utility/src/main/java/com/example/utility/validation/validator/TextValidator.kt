package com.example.utility.validation.validator

import com.example.utility.validation.ValidatorErrorMessage
import java.util.regex.Pattern
import kotlin.io.path.name


data object TextValidator : Validator {
    override fun validate(value: String): ValidatorErrorMessage.NormalText? =
        if(value.isBlank()){
            ValidatorErrorMessage.NormalText.EMPTY_FIELD
        }else null
}

