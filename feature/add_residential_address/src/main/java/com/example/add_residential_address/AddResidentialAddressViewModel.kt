package com.example.add_residential_address

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.request.AddressRequest
import com.example.network.remote.add_residential_address.AddResidentialAddressApi
import com.example.utility.network.Error
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddResidentialAddressViewModel(
    private val addResidentialAddressApi:AddResidentialAddressApi,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddAddressUiState())
    val uiState = _uiState.asStateFlow()

    fun getUiActions(
        navActions: AddResidentialAddressNavigationUiActions,
    ): AddResidentialAddressUiActions = AddResidentialAddressUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): AddResidentialAddressBusinessUiActions = object : AddResidentialAddressBusinessUiActions {
        override fun onGovernorateTextChange(governorate: String) {
            updateGovernorateText(governorate)
        }

        override fun onCityTextChange(city: String) {
            updateCityText(city)
        }

        override fun onRegionTextChange(region: String) {
            updateRegionText(region)
        }

        override fun onStreetTextChange(street: String) {
            updateStreetText(street)
        }

        override fun onNoteTextChange(note: String) {
            updateNoteText(note)
        }

        override fun onAddressInfoVisibilityPolicyCheckChange(value: Boolean) {
            updateAddressInfoVisibilityPolicyCheckState(value)
        }


        override fun onShowErrorDialogStateChange(value: Boolean) {
            updateShowErrorDialogState(value)
        }


        override fun onSubmitButtonClick() {
            submitAddress()
        }
    }

    private fun updateGovernorateText(value: String) {
        _uiState.update {
            it.copy(
                governorate = value
            )
        }
    }

    private fun updateCityText(value: String) {
        _uiState.update {
            it.copy(
                city = value
            )
        }
    }

    private fun updateRegionText(value: String) {
        _uiState.update {
            it.copy(
                region = value
            )
        }
    }

    private fun updateStreetText(value: String) {
        _uiState.update {
            it.copy(
                street = value
            )
        }
    }

    private fun updateNoteText(value: String) {
        _uiState.update {
            it.copy(
                note = value
            )
        }
    }



    private fun updateAddressInfoVisibilityPolicyCheckState(value: Boolean) {
        _uiState.update {
            it.copy(
                isAddressInfoPolicyChecked = value
            )
        }
    }

    private fun updateIsLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateErrorState(error: Error?) {
        _uiState.update { it.copy(error = error) }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateIsSuccessfulState(isSuccessful: Boolean) {
        _uiState.update { it.copy(isSuccessful = isSuccessful) }
    }

    private fun submitAddress() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Submitting Address info","AddResidentialViewModel")
            val result = addResidentialAddressApi.addResidentialAddress(
                addressRequest = AddressRequest(
                    governorate = uiState.value.governorate,
                    city = uiState.value.city,
                    region = uiState.value.region,
                    street = uiState.value.street,
                    note = uiState.value.note,
                )
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Address added Successfully","AddResidentialViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                    updateIsSuccessfulState(true)
                }

                is Result.Error -> {
                    Log.v("Failed sign up","AddResidentialViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                    updateIsSuccessfulState(false)
                }
            }
        }
    }
}

