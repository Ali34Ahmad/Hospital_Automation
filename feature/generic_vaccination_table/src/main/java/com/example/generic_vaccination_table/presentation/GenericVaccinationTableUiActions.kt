package com.example.generic_vaccination_table.presentation


class GenericVaccinationTableUiActions(
    navigationActions: GenericVaccinationTableNavigationUiActions,
    businessActions: GenericVaccinationTableBusinessUiActions,
) : GenericVaccinationTableBusinessUiActions by businessActions,
    GenericVaccinationTableNavigationUiActions by navigationActions


interface GenericVaccinationTableBusinessUiActions {
    fun onAddVaccineToVisitClick(visitNumber: Int)
    fun onHideVaccinesDialog()
    fun onCancelVaccinesFetch()
    fun onShowVaccinesDialog()
    fun onRemoveVaccineFromVisit(visitNumber: Int, vaccineIndex: Int)
    fun onSetVisitNumberAndVaccineIndex(visitNumber: Int, vaccineIndex: Int)
    fun onShowDiscardChangesConfirmationDialog()
    fun hideConfirmationDialog()
    fun onShowDeleteConfirmationDialog()
    fun onAddVaccinesToVisit(indexes: List<Int>)
    fun onAddNewVisit()
    fun onUpdateConfirmationDialogVisibility(isVisible: Boolean)
    fun onCleanUpSelectedVaccinesIndices()
    fun onVaccineOptionSelected(index:Int,isSelected: Boolean)
    fun onRefresh()
    fun clearToastMessage()
    fun onRetryFetchVaccinesWithNoVisitNumber()
}

interface GenericVaccinationTableNavigationUiActions {
    fun navigateToVaccineDetailsScreen(vaccineId: Int)
    fun navigateUp()
}