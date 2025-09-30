package com.example.add_new_vaccine.validator

import com.example.add_new_vaccine.presentation.AddNewVaccineUiState
import com.example.domain.use_cases.validator.ValidatePositiveNumberUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
import com.example.model.enums.AgeUnit
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.validation.ValidatorErrorMessage

class AddNewVaccineValidator(
    private val validateTextUseCase: ValidateTextUseCase,
    private val validatePositiveNumberUseCase: ValidatePositiveNumberUseCase,
) {
    fun validate(state: AddNewVaccineUiState): AddNewVaccineValidationResult {
        val vaccineNameError = validateNormalText(state.vaccineName)

        val fromAgeError = validatePositiveNumber(state.fromAge)
        val fromAgeUnitError = validateAgeUnit(state.fromAgeUnit)
        val toAgeError = validatePositiveNumber(state.toAge)
        val toAgeUnitError = validateAgeUnit(state.toAgeUnit)

        val quantityError = validatePositiveNumber(state.quantity)

        val vaccineDescriptionError = validateNormalText(state.vaccineDescription)

        return AddNewVaccineValidationResult(
            vaccineNameError = vaccineNameError,
            fromAgeError = fromAgeError,
            fromAgeUnitError = fromAgeUnitError,
            toAgeError = toAgeError,
            toAgeUnitError = toAgeUnitError,
            quantityError = quantityError,
            vaccineDescriptionError = vaccineDescriptionError,
        )
    }

    private fun validateNormalText(text: String): UiText? {
        return when (validateTextUseCase(text)) {
            ValidatorErrorMessage.NormalText.EMPTY_FIELD -> UiText.StringResource(R.string.required_field)
            null -> null
        }
    }

    private fun validatePositiveNumber(text: String): UiText? {
        return when(validatePositiveNumberUseCase(text)){
            ValidatorErrorMessage.PositiveNumber.EMPTY_FIELD-> UiText.StringResource(R.string.required_field)
            ValidatorErrorMessage.PositiveNumber.INVALID_NUMBER-> UiText.StringResource(R.string.invalid_number)
            ValidatorErrorMessage.PositiveNumber.NEGATIVE_NUMBER-> UiText.StringResource(R.string.number_must_be_positive)
            null -> null
        }
    }

    private fun validateAgeUnit(ageUnit: AgeUnit): UiText? {
        if (ageUnit == AgeUnit.NONE) {
            return UiText.StringResource(R.string.age_unit_must_be_specified)
        }
        return null
    }

}