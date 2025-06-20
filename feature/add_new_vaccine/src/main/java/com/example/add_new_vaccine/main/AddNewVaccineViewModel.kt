package com.example.add_new_vaccine.main

import androidx.lifecycle.ViewModel
import com.example.add_new_vaccine.validator.AddNewVaccineValidator
import com.example.model.enums.AgeUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class AddNewVaccineViewModel(
    private val addNewVaccineValidator: AddNewVaccineValidator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddNewVaccineUiState())
    val uiState = _uiState.asStateFlow()

    fun getUiActions(
        navActions: AddNewVaccineNavigationUiActions,
    ): AddNewVaccineUiActions = AddNewVaccineUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): AddNewVaccineBusinessUiActions =
        object : AddNewVaccineBusinessUiActions {
            override fun onVaccineNameChange(vaccineName: String) {
                TODO("Not yet implemented")
            }

            override fun onFromAgeChange(fromAge: String) {
                TODO("Not yet implemented")
            }

            override fun onFromAgeMenuExpandedChange(isVisible: Boolean) {
                TODO("Not yet implemented")
            }

            override fun updateSelectedFromAgeUnitIndex(selectedUnit: AgeUnit) {
                TODO("Not yet implemented")
            }

            override fun onToAgeChange(toAge: String) {
                TODO("Not yet implemented")
            }

            override fun onToAgeMenuExpandedChange(isVisible: Boolean) {
                TODO("Not yet implemented")
            }

            override fun updateSelectedToAgeUnitIndex(selectedUnit: AgeUnit) {
                TODO("Not yet implemented")
            }

            override fun onQuantityChange(quantity: String) {
                TODO("Not yet implemented")
            }

            override fun onVaccineDescriptionChange(vaccineDescription: String) {
                TODO("Not yet implemented")
            }

            override fun onInteractionNameChange(interactionName: String) {
                TODO("Not yet implemented")
            }

            override fun onInteractionDescriptionChange(interactionDescription: String) {
                TODO("Not yet implemented")
            }

            override fun onAddInteractionClick() {
                TODO("Not yet implemented")
            }

            override fun onShowErrorDialogStateChange(value: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onSubmitButtonClick() {
                TODO("Not yet implemented")
            }

        }


}