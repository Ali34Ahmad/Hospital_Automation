package com.example.signup.doctor.validator

import com.example.domain.use_cases.validator.ValidateConfirmPasswordUseCase
import com.example.domain.use_cases.validator.ValidateEmailUseCase
import com.example.domain.use_cases.validator.ValidatePasswordUseCase
import com.example.domain.use_cases.validator.ValidatePhoneNumberUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
import com.example.signup.doctor.DoctorSignUpUiState
import com.example.signup.main.SignUpUiState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.validation.ValidatorErrorMessage

class DoctorSignUpValidator(
    private val validateTextUseCase: ValidateTextUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase
){
    fun validate(state: DoctorSignUpUiState): DoctorSignUpValidationResult {
        val firstNameError = validateNormalText(state.firstName)
        val middleNameError = validateNormalText(state.middleName)
        val lastNameError = validateNormalText(state.lastName)
        val specialtyError = validateNormalText(state.specialty)
        val emailError = validateEmail(state.email)
        val phoneNumberError = validatePhoneNumber(state.phoneNumber)
        val passwordError = validatePassword(state.password)
        val confirmPasswordError = validateConfirmPassword(state.confirmPassword, state.password)

        return DoctorSignUpValidationResult(
            firstNameError = firstNameError,
            middleNameError = middleNameError,
            lastNameError = lastNameError,
            specialtyError = specialtyError,
            emailError = emailError,
            phoneNumberError = phoneNumberError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        )
    }
    
    private fun validateEmail(email: String): UiText? {
        return when (validateEmailUseCase(email)) {
            ValidatorErrorMessage.Email.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.Email.INVALID_EMAIL -> UiText.StringResource(R.string.invalid_email)
            null -> null
        }
    }

    private fun validateNormalText(text: String): UiText? {
        return when (validateTextUseCase(text)) {
            ValidatorErrorMessage.NormalText.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            null -> null
        }
    }

    private fun validatePassword(text: String): UiText? {
        return when (validatePasswordUseCase(text)) {
            ValidatorErrorMessage.Password.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.Password.LESS_THAN_EIGHT_CHARS -> UiText.StringResource(R.string.password_must_contains_more_than_eight_letters)
            null -> null
        }
    }

    private fun validateConfirmPassword(text: String, password: String): UiText? {
        return when (validateConfirmPasswordUseCase(text, password)) {
            ValidatorErrorMessage.ConfirmPassword.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.ConfirmPassword.DOES_NOT_MATCH_PASSWORD -> UiText.StringResource(R.string.does_not_match_password)
            null -> null
        }
    }

    private fun validatePhoneNumber(text: String): UiText? {
        return when (validatePhoneNumberUseCase(text)) {
            ValidatorErrorMessage.PhoneNumber.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.PhoneNumber.INVALID_PHONE_NUMBER -> UiText.StringResource(R.string.invalid_phone_number)
            null -> null
        }
    }
}