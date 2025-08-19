package com.example.vaccines.main

import com.example.model.enums.ScreenState


fun mockAllVaccinesNavigationUiActions() = object : AllVaccinesNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToVaccineDetailsScreen(vaccineId: Int) {

    }

}

fun mockAllVaccinesBusinessUiActions() = object : AllVaccinesBusinessUiActions {
    override fun onRefresh() {

    }

    override fun onUpdateScreenState(screenState: ScreenState) {

    }

    override fun clearToastMessage() {

    }

}
