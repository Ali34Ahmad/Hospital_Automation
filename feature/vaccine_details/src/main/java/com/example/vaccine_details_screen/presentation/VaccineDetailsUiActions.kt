package com.example.vaccine_details_screen.presentation

import com.example.utility.ui.AppNavigationUiAction


class VaccineDetailsUiActions(
    navigationActions:VaccineDetailsNavigationUiActions,
    businessActions:VaccineDetailsBusinessUiActions,
) :VaccineDetailsBusinessUiActions by businessActions,
    VaccineDetailsNavigationUiActions by navigationActions


interface VaccineDetailsBusinessUiActions {
    fun onRefresh()
    fun clearToastMessage()
}

interface VaccineDetailsNavigationUiActions:AppNavigationUiAction {
    fun navigateToVaccinationTableScreen()
    fun navigateUp()
}