package com.example.vaccines.main

import com.example.model.enums.ScreenState
import com.example.utility.ui.AppNavigationUiAction

class AllVaccinesUiActions(
    navigationActions: AllVaccinesNavigationUiActions,
    businessActions: AllVaccinesBusinessUiActions,
) : AllVaccinesBusinessUiActions by businessActions,
    AllVaccinesNavigationUiActions by navigationActions


interface AllVaccinesBusinessUiActions {
    fun onRefresh()
    fun onUpdateScreenState(screenState: ScreenState)
    fun clearToastMessage()
}

interface AllVaccinesNavigationUiActions : AppNavigationUiAction {
    fun navigateUp()
    fun navigateToVaccineDetailsScreen(vaccineId:Int)
}
