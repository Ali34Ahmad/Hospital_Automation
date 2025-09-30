package com.example.vaccines.presentation

import com.example.model.enums.ScreenState


fun mockAllVaccinesNavigationUiActions() = object : VaccinesNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToVaccineDetailsScreen(vaccineId: Int) {

    }

    override fun navigateToAddNewVaccineScreen() {

    }

}

fun mockAllVaccinesBusinessUiActions() = object : VaccinesBusinessUiActions {
    override fun onRefresh() {

    }

    override fun onUpdateScreenState(screenState: ScreenState) {

    }

    override fun clearToastMessage() {

    }

}
