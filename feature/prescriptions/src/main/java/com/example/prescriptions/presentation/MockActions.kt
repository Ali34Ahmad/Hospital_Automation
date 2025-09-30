package com.example.prescriptions.presentation

import com.example.model.enums.ScreenState


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

    override fun onDeleteQuery() {

    }

    override fun onRefresh() {

    }

    override fun onUpdateScreenState(screenState: ScreenState) {

    }

    override fun clearToastMessage() {

    }

    override fun onShowSearchBar() {

    }

    override fun onHideSearchBar() {

    }

}