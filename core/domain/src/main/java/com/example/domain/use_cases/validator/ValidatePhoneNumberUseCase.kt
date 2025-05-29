package com.example.domain.use_cases.validator

import com.example.utility.validation.validator.PhoneNumberValidatorWithLib
import com.example.utility.validation.ValidatorErrorMessage

class ValidatePhoneNumberUseCase {
    operator fun invoke(phoneNumber: String): ValidatorErrorMessage.PhoneNumber? {
        return PhoneNumberValidatorWithLib.validate(phoneNumber)
    }
}