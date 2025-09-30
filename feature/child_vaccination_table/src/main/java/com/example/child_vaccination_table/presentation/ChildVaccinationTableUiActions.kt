package com.example.child_vaccination_table.presentation


class ChildVaccinationTableUiActions(
    navigationActions: ChildVaccinationTableNavigationUiActions,
    businessActions: ChildVaccinationTableBusinessUiActions,
) : ChildVaccinationTableBusinessUiActions by businessActions,
    ChildVaccinationTableNavigationUiActions by navigationActions


interface ChildVaccinationTableBusinessUiActions {
    fun onRefresh()
    fun clearToastMessage()
}

interface ChildVaccinationTableNavigationUiActions {
    fun navigateToVaccineDetailsScreen(vaccineId: Int)
    fun navigateToAppointmentDetailsScreen(appointmentId:Int)
    fun navigateUp()
}