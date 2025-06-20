package com.example.add_new_vaccine.main

import com.example.model.enums.AgeUnit
import com.example.model.enums.Gender


fun mockNavigationAction() = object : AddNewVaccineNavigationUiActions {
    override fun navigateToVaccineDetailsScreenScreen() {

    }

    override fun navigateUp() {

    }

}

fun mockBusinessUiActions() = object : AddNewVaccineBusinessUiActions {
    override fun onVaccineNameChange(vaccineName: String) {

    }

    override fun onFromAgeChange(fromAge: String) {

    }

    override fun onFromAgeMenuExpandedChange(isVisible: Boolean) {

    }

    override fun updateSelectedFromAgeUnitIndex(selectedUnit: AgeUnit) {

    }

    override fun onToAgeChange(toAge: String) {

    }

    override fun onToAgeMenuExpandedChange(isVisible: Boolean) {

    }

    override fun updateSelectedToAgeUnitIndex(selectedUnit: AgeUnit) {

    }

    override fun onQuantityChange(quantity: String) {

    }

    override fun onVaccineDescriptionChange(vaccineDescription: String) {

    }

    override fun onInteractionNameChange(interactionName: String) {

    }

    override fun onInteractionDescriptionChange(interactionDescription: String) {

    }

    override fun onAddInteractionClick() {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onSubmitButtonClick() {

    }

}