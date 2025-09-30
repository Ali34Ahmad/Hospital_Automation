package com.example.medical_records.presentation

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState


fun mockAllVaccinesNavigationUiActions() = object :
    MedicalRecordsNavigationUiActions {
    override fun navigateUp() {

    }

    override fun navigateToAppointmentsScreen(
        patientId: Int?,
        childId: Int?
    ) {

    }

    override fun navigateToPrescriptionsScreen(patientId: Int?, childId: Int?,doctorId:Int?) {

    }


}

fun mockAllVaccinesBusinessUiActions() = object :
    MedicalRecordsBusinessUiActions {
    override fun onUpdateSearchText(searchText: String) {

    }

    override fun onDeleteQuery() {

    }

    override fun onChangeToolBarMode(topBarMode: TopBarState) {

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