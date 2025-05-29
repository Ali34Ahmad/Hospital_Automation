package com.example.email_verification.otp_verification.validator

import com.example.domain.use_cases.validator.ValidateOtpUseCase
import com.example.email_verification.otp_verification.main.OtpVerificationUiState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.validation.ValidatorErrorMessage

class OtpVerificationValidator(
    private val validateOtpUseCase: ValidateOtpUseCase
) {
    fun validate(state: OtpVerificationUiState): OtpValidationResult {
        val otpError = validateOtp(state.otpCode.joinToString(separator = ""))

        return OtpValidationResult(
            otpError = otpError
        )
    }

    private fun validateOtp(otp: String): UiText? {
        return when (validateOtpUseCase(otp)) {
            ValidatorErrorMessage.OtpError.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.OtpError.INVALID_LENGTH -> UiText.StringResource(R.string.otp_invalid_length)
            ValidatorErrorMessage.OtpError.NON_DIGIT_CHARACTER -> UiText.StringResource(R.string.otp_must_be_digits)
            null -> null
        }
    }
}