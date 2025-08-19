package com.example.medical_records.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.utility.ui.AppNavigationUiAction


class MedicalRecordsUiActions(
    navigationActions: MedicalRecordsNavigationUiActions,
    businessActions: MedicalRecordsBusinessUiActions,
) : MedicalRecordsBusinessUiActions by businessActions,
    MedicalRecordsNavigationUiActions by navigationActions


interface MedicalRecordsBusinessUiActions {
    fun onUpdateSearchText(searchText: String)
    fun onChangeToolBarMode(topBarMode:TopBarState)
    fun onRefresh()
    fun onUpdateScreenState(screenState: ScreenState)
    fun clearToastMessage()
}

interface MedicalRecordsNavigationUiActions : AppNavigationUiAction {
    fun navigateUp()
    fun navigateToAppointmentsScreen(patientId:Int?, childId:Int?)
    fun navigateToPrescriptionsScreen(patientId:Int?, childId:Int?)
}
