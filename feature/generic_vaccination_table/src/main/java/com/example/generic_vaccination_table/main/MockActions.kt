package com.example.generic_vaccination_table.main


fun mockEmployeeProfileNavigationUiActions()=object : GenericVaccinationTableNavigationUiActions {
    override fun navigateToVaccineDetailsScreen(vaccineId: Int) {

    }

    override fun navigateUp() {

    }

}

fun mockEmployeeProfileBusinessUiActions()=object : GenericVaccinationTableBusinessUiActions {
    override fun onSaveTableChanges() {

    }

    override fun onAddVaccineToVisitClick(visitNumber: Int) {

    }

    override fun onHideVaccinesDialog() {

    }

    override fun onShowVaccinesDialog() {

    }

    override fun onRemoveVaccineFromVisit(visitNumber: Int, vaccineIndex: Int) {

    }

    override fun onSetVisitNumberAndVaccineIndex(
        visitNumber: Int,
        vaccineIndex: Int
    ) {

    }

    override fun onShowDiscardChangesConfirmationDialog() {

    }

    override fun hideConfirmationDialog() {

    }

    override fun onShowDeleteConfirmationDialog() {

    }

    override fun onAddVaccinesToVisit(indexes: List<Int>) {

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
