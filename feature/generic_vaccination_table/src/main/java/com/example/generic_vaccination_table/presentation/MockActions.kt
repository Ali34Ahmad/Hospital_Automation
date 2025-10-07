package com.example.generic_vaccination_table.presentation


fun mockNavigationUiActions()=object : GenericVaccinationTableNavigationUiActions {
    override fun navigateToVaccineDetailsScreen(vaccineId: Int) {

    }

    override fun navigateUp() {

    }

}

fun mockBusinessUiActions()=object : GenericVaccinationTableBusinessUiActions {
    override fun onAddVaccineToVisitClick(visitNumber: Int) {

    }

    override fun onHideVaccinesDialog() {

    }

    override fun onCancelVaccinesFetch() {

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

    override fun onUpdateConfirmationDialogVisibility(isVisible: Boolean) {

    }

    override fun onCleanUpSelectedVaccinesIndices() {

    }

    override fun onVaccineOptionSelected(index: Int, isSelected: Boolean) {

    }

    override fun onRefresh() {

    }

    override fun clearToastMessage() {

    }

    override fun onRetryFetchVaccinesWithNoVisitNumber() {

    }


}
