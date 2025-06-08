package com.example.add_residential_address.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.add_residential_address.validator.AddResidentialAddressValidationResult
import com.example.add_residential_address.validator.AddResidentialAddressValidator
import com.example.domain.use_cases.add_residential_address.AddResidentialAddressUseCase
import com.example.model.enums.ScreenState
import com.example.model.residential_address.AddAddressRequest
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddResidentialAddressViewModel(
    private val addResidentialAddressUseCase: AddResidentialAddressUseCase,
    private val addResidentialAddressValidator: AddResidentialAddressValidator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddAddressUiState())
    val uiState = _uiState.asStateFlow()

    fun getUiActions(
        navActions: AddResidentialAddressNavigationUiActions,
    ): AddResidentialAddressUiActions = AddResidentialAddressUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): AddResidentialAddressBusinessUiActions =
        object : AddResidentialAddressBusinessUiActions {
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
                validateAndSubmitAddress()
            }
        }

    private fun updateGovernorateText(value: String) {
        _uiState.update {
            it.copy(
                governorate = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateCityText(value: String) {
        _uiState.update {
            it.copy(
                city = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateRegionText(value: String) {
        _uiState.update {
            it.copy(
                region = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateStreetText(value: String) {
        _uiState.update {
            it.copy(
                street = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateNoteText(value: String) {
        _uiState.update {
            it.copy(
                note = value
            )
        }
        updateSignUpButtonEnablement()
    }


    private fun updateAddressInfoVisibilityPolicyCheckState(value: Boolean) {
        _uiState.update {
            it.copy(
                isAddressInfoPolicyChecked = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateSignUpButtonEnablement() {
        _uiState.update { currentState ->
            val allFieldsFilled = currentState.governorate.isNotEmpty() &&
                    currentState.city.isNotEmpty() &&
                    currentState.region.isNotEmpty() &&
                    currentState.street.isNotEmpty()

            val checkBoxesChecked = currentState.isAddressInfoPolicyChecked

            currentState.copy(isSubmitButtonEnabled = allFieldsFilled && checkBoxesChecked)
        }
    }

    private fun updateValidationErrors(result: AddResidentialAddressValidationResult) {
        _uiState.update {
            it.copy(
                governorateError = result.governorateError,
                cityError = result.cityError,
                regionError = result.regionError,
                streetError = result.streetError,
                noteError = result.noteError,
            )
        }
    }

    private fun updateScreenState(screenState:ScreenState){
        _uiState.update {
            it.copy(
                screenState = screenState
            )
        }
    }

    private fun submitAddress() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Submitting Address info", "AddResidentialViewModel")
            addResidentialAddressUseCase(
                addAddressRequest = AddAddressRequest(
                    governorate = uiState.value.governorate,
                    city = uiState.value.city,
                    region = uiState.value.region,
                    street = uiState.value.street,
                    note = uiState.value.note,
                )
            ).onSuccess { result ->
                Log.v("Address added Successfully", "AddResidentialViewModel")
                updateScreenState(ScreenState.SUCCESS)
                updateShowErrorDialogState(false)
            }.onError { error ->
                Log.v("Adding Address Failed", "AddResidentialViewModel")
                updateScreenState(ScreenState.ERROR)
                updateShowErrorDialogState(true)
            }
        }
    }

    private fun validateAndSubmitAddress() {
        val validationResult = addResidentialAddressValidator.validate(uiState.value)
        updateValidationErrors(validationResult)
        if (!validationResult.hasErrors()) {
            submitAddress()
        }
    }
}

