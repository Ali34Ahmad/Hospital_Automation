package com.example.add_new_vaccine.main

import com.example.model.enums.AgeUnit

class AddNewVaccineUiActions(
    navigationActions: AddNewVaccineNavigationUiActions,
    businessActions: AddNewVaccineBusinessUiActions,
) : AddNewVaccineBusinessUiActions by businessActions,
    AddNewVaccineNavigationUiActions by navigationActions


interface AddNewVaccineBusinessUiActions {
    fun onVaccineNameChange(vaccineName: String)

    fun onFromAgeChange(fromAge: String)
    fun onFromAgeMenuExpandedChange(isVisible: Boolean)
    fun updateSelectedFromAgeUnitIndex(selectedUnit: AgeUnit)

    fun onToAgeChange(toAge: String)
    fun onToAgeMenuExpandedChange(isVisible: Boolean)
    fun updateSelectedToAgeUnitIndex(selectedUnit: AgeUnit)

    fun onQuantityChange(quantity: String)
    fun onVaccineDescriptionChange(vaccineDescription: String)
    fun onInteractionNameChange(interactionName: String)
    fun onInteractionDescriptionChange(interactionDescription: String)
    fun onAddInteractionClick()

    fun onShowErrorDialogStateChange(value: Boolean)
    fun onSubmitButtonClick()
}

interface AddNewVaccineNavigationUiActions {
    fun navigateToVaccineDetailsScreenScreen()
    fun navigateUp()
}