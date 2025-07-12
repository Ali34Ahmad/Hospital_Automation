package com.example.generic_vaccination_table.main


fun mockEmployeeProfileNavigationUiActions()=object : GenericVaccinationTableNavigationUiActions {
    override fun navigateToVaccineDetailsScreen() {

    }

    override fun navigateToVaccinesScreen() {

    }

    override fun navigateUp() {

    }

}

fun mockEmployeeProfileBusinessUiActions()=object : GenericVaccinationTableBusinessUiActions {
    override fun onSaveTableChanges() {

    }

    override fun onAddVaccineToVisit(visitNumber: Int) {

    }

    override fun onRemoveVaccineFromVisit(visitNumber: Int, vaccineIndex: Int) {

    }

    override fun onAddNewVisit() {

    }

    override fun onUpdateVaccinesSelectionDialogVisibility(isVisible: Boolean) {

    }

    override fun onUpdateConfirmationDialogVisibility(isVisible: Boolean) {

    }

    override fun onGetGenericVaccinationTable() {

    }

    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }


}
