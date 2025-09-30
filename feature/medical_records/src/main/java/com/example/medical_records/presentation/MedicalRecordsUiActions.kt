package com.example.medical_records.presentation

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState


class MedicalRecordsUiActions(
    navigationActions: MedicalRecordsNavigationUiActions,
    businessActions: MedicalRecordsBusinessUiActions,
) : MedicalRecordsBusinessUiActions by businessActions,
    MedicalRecordsNavigationUiActions by navigationActions


interface MedicalRecordsBusinessUiActions {
    fun onUpdateSearchText(searchText: String)
    fun onDeleteQuery()
    fun onChangeToolBarMode(topBarMode: TopBarState)
    fun onRefresh()
    fun onUpdateScreenState(screenState: ScreenState)
    fun clearToastMessage()
    fun onShowSearchBar()
    fun onHideSearchBar()
}

interface MedicalRecordsNavigationUiActions {
    fun navigateUp()
    fun navigateToAppointmentsScreen(patientId: Int?, childId: Int?)
    fun navigateToPrescriptionsScreen(
        patientId: Int?,
        childId: Int?,
        doctorId: Int?,
    )
}
