package com.example.add_new_vaccine.validator

import com.example.util.UiText

data class AddNewVaccineValidationResult(
    val vaccineNameError: UiText? = null,
    val fromAgeError: UiText? = null,
    val fromAgeUnitError: UiText? = null,
    val toAgeError: UiText? = null,
    val toAgeUnitError: UiText? = null,
    val quantityError: UiText? = null,
    val vaccineDescriptionError: UiText? = null
) {
    fun hasErrors(): Boolean {
        return listOfNotNull(
            vaccineNameError,
            fromAgeError,
            fromAgeUnitError,
            toAgeError,
            toAgeUnitError,
            quantityError,
            vaccineDescriptionError
        ).isNotEmpty()
    }
}