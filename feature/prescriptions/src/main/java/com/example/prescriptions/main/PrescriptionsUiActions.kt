package com.example.prescriptions.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.utility.ui.AppNavigationUiAction


class PrescriptionsUiActions(
    navigationActions: PrescriptionsNavigationUiActions,
    businessActions: PrescriptionsBusinessUiActions,
) : PrescriptionsBusinessUiActions by businessActions,
    PrescriptionsNavigationUiActions by navigationActions


interface PrescriptionsBusinessUiActions {
    fun onUpdateSearchText(searchText: String)
    fun onDeleteQuery()
    fun onRefresh()
    fun onUpdateScreenState(screenState: ScreenState)
    fun clearToastMessage()
    fun onShowSearchBar()
    fun onHideSearchBar()
}

interface PrescriptionsNavigationUiActions : AppNavigationUiAction {
    fun navigateUp()
    fun navigateToPrescriptionDetailsScreen(prescriptionId:Int?)
}
