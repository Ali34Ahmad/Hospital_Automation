package com.example.medical_prescriptions.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.utility.ui.AppNavigationUiAction


class MedicalPrescriptionsUiActions(
    navigationActions: MedicalPrescriptionsNavigationUiActions,
    businessActions: MedicalPrescriptionsBusinessUiActions,
) : MedicalPrescriptionsBusinessUiActions by businessActions,
    MedicalPrescriptionsNavigationUiActions by navigationActions


interface MedicalPrescriptionsBusinessUiActions {
    fun onUpdateSearchText(searchText: String)
    fun onChangeToolBarMode(topBarMode:TopBarState)
    fun onRefresh()
    fun onUpdateScreenState(screenState: ScreenState)
    fun clearToastMessage()
}

interface MedicalPrescriptionsNavigationUiActions : AppNavigationUiAction {
    fun navigateUp()
    fun navigateToMedicalPrescriptionDetailsScreen(prescriptionId:Int?)
}
