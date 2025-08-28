package com.example.vaccines.main

import com.example.model.enums.ScreenState

class VaccinesUiActions(
    navigationActions: VaccinesNavigationUiActions,
    businessActions: VaccinesBusinessUiActions,
) : VaccinesBusinessUiActions by businessActions,
    VaccinesNavigationUiActions by navigationActions


interface VaccinesBusinessUiActions {
    fun onRefresh()
    fun onUpdateScreenState(screenState: ScreenState)
    fun clearToastMessage()
}

interface VaccinesNavigationUiActions {
    fun navigateUp()
    fun navigateToVaccineDetailsScreen(vaccineId:Int)
}
