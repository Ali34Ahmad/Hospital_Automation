package com.example.add_residential_address.main

import com.example.util.UiText
import com.example.utility.network.Error


data class AddAddressUiState(
    val governorate: String = "",
    val governorateError: UiText? = null,
    val city: String = "",
    val cityError: UiText? = null,
    val region: String = "",
    val regionError: UiText? = null,
    val street: String = "",
    val streetError: UiText? = null,
    val note: String = "",
    val noteError: UiText? = null,
    val isAddressInfoPolicyChecked: Boolean = false,
    val isSubmitButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessful: Boolean = false,
)