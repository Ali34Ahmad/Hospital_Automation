package com.example.add_residential_address.validator

import com.example.util.UiText

data class AddResidentialAddressValidationResult(
    val governorateError: UiText? = null,
    val cityError: UiText? = null,
    val regionError: UiText? = null,
    val streetError: UiText? = null,
    val noteError: UiText? = null,
) {
    fun hasErrors(): Boolean {
        return listOfNotNull(
            governorateError,
            cityError,
            regionError,
            streetError,
            noteError
        ).isNotEmpty()
    }
}