package com.example.add_residential_address.validator

import com.example.add_residential_address.main.AddAddressUiState
import com.example.domain.use_cases.validator.ValidateConfirmPasswordUseCase
import com.example.domain.use_cases.validator.ValidateEmailUseCase
import com.example.domain.use_cases.validator.ValidatePasswordUseCase
import com.example.domain.use_cases.validator.ValidatePhoneNumberUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.validation.ValidatorErrorMessage

class AddResidentialAddressValidator(
    private val validateTextUseCase: ValidateTextUseCase,
){
    fun validate(state: AddAddressUiState): AddResidentialAddressValidationResult {
        val governorateError = validateNormalText(state.governorate)
        val cityError = validateNormalText(state.city)
        val regionError = validateNormalText(state.region)
        val streetError = validateNormalText(state.street)

        return AddResidentialAddressValidationResult(
            governorateError = governorateError,
            cityError = cityError,
            regionError = regionError,
            streetError = streetError,
        )
    }

    private fun validateNormalText(text: String): UiText? {
        return when (validateTextUseCase(text)) {
            ValidatorErrorMessage.NormalText.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            null -> null
        }
    }
}