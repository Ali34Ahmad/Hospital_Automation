package com.example.medical_prescriptions.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState


fun mockAllVaccinesNavigationUiActions() = object :
    MedicalPrescriptionsNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToMedicalPrescriptionDetailsScreen(prescriptionId: Int?) {

    }
}

fun mockAllVaccinesBusinessUiActions() = object :
    MedicalPrescriptionsBusinessUiActions {
    override fun onUpdateSearchText(searchText: String) {

    }

    override fun onChangeToolBarMode(topBarMode: TopBarState) {

    }

    override fun onRefresh() {

    }

    override fun onUpdateScreenState(screenState: ScreenState) {

    }

    override fun clearToastMessage() {

    }

}