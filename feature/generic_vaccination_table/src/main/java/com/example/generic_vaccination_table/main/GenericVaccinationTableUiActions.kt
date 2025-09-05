package com.example.generic_vaccination_table.main


class GenericVaccinationTableUiActions(
    navigationActions: GenericVaccinationTableNavigationUiActions,
    businessActions: GenericVaccinationTableBusinessUiActions,
) : GenericVaccinationTableBusinessUiActions by businessActions,
    GenericVaccinationTableNavigationUiActions by navigationActions


interface GenericVaccinationTableBusinessUiActions {
    fun onAddVaccineToVisitClick(visitNumber: Int)
    fun onHideVaccinesDialog()
    fun onShowVaccinesDialog()
    fun onRemoveVaccineFromVisit(visitNumber: Int, vaccineIndex: Int)
    fun onSetVisitNumberAndVaccineIndex(visitNumber: Int, vaccineIndex: Int)
    fun onShowDiscardChangesConfirmationDialog()
    fun hideConfirmationDialog()
    fun onShowDeleteConfirmationDialog()
    fun onAddVaccinesToVisit(indexes: List<Int>)
    fun onAddNewVisit()
    fun onUpdateVaccinesSelectionDialogVisibility(isVisible: Boolean)
    fun onUpdateConfirmationDialogVisibility(isVisible: Boolean)
    fun onGetGenericVaccinationTable()
    fun onRefresh()
    fun clearToastMessage()
}

interface GenericVaccinationTableNavigationUiActions {
    fun navigateToVaccineDetailsScreen(vaccineId: Int)
    fun navigateUp()
}