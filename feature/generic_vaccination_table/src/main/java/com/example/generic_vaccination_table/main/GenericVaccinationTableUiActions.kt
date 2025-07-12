package com.example.generic_vaccination_table.main

import com.example.utility.ui.AppNavigationUiAction


class GenericVaccinationTableUiActions(
    navigationActions:GenericVaccinationTableNavigationUiActions,
    businessActions:GenericVaccinationTableBusinessUiActions,
) :GenericVaccinationTableBusinessUiActions by businessActions,
    GenericVaccinationTableNavigationUiActions by navigationActions


interface GenericVaccinationTableBusinessUiActions {
    fun onSaveTableChanges()
    fun onAddVaccineToVisit(visitNumber: Int)
    fun onRemoveVaccineFromVisit(visitNumber: Int,vaccineIndex: Int)
    fun onAddNewVisit()
    fun onUpdateVaccinesSelectionDialogVisibility(isVisible: Boolean)
    fun onUpdateConfirmationDialogVisibility(isVisible: Boolean)
    fun onGetGenericVaccinationTable()
    fun onRefresh()
    fun clearToastMessage()
}

interface GenericVaccinationTableNavigationUiActions:AppNavigationUiAction {
    fun navigateToVaccineDetailsScreen()
    fun navigateToVaccinesScreen()
    fun navigateUp()
}