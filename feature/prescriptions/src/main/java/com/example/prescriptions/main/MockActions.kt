package com.example.prescriptions.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState


fun mockAllVaccinesNavigationUiActions() = object :
    PrescriptionsNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToPrescriptionDetailsScreen(prescriptionId: Int?) {

    }
}

fun mockAllVaccinesBusinessUiActions() = object :
    PrescriptionsBusinessUiActions {
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