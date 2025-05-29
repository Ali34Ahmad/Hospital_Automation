package com.example.domain.use_cases.validator

import com.example.utility.validation.ValidatorErrorMessage
import com.example.utility.validation.validator.OtpValidator

class ValidateOtpUseCase {
    operator fun invoke(otp: String): ValidatorErrorMessage.OtpError? {
        return OtpValidator.validate(otp)
    }
}