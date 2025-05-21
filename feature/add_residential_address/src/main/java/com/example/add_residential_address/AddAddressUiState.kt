package com.example.add_residential_address

import com.example.utility.network.Error


data class AddAddressUiState(
    val governorate: String = "",
    val governorateError: String? = null,
    val city: String = "",
    val cityError: String? = null,
    val region: String = "",
    val regionError: String? = null,
    val street: String = "",
    val streetError: String? = null,
    val note: String = "",
    val noteError: String? = null,
    val isAddressInfoPolicyChecked: Boolean = false,
    val isSubmitButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val isSuccessful: Boolean = false,
)