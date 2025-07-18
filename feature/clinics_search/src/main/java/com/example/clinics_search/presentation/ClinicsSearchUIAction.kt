package com.example.clinics_search.presentation

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState

interface ClinicsSearchUIAction {
    object GoToSelectionStep: ClinicsSearchUIAction
    data class UpdateTopBarMode(val state: TopBarState): ClinicsSearchUIAction
    data class UpdateQuery(val newQuery: String): ClinicsSearchUIAction
    data class UpdateScreenState(val newState: ScreenState): ClinicsSearchUIAction
    object ToggleTheme : ClinicsSearchUIAction
    object Refresh: ClinicsSearchUIAction
}

interface ClinicsSearchNavigationActions{
    fun navigateToDepartmentDetails(clinicId: Int,doctorId: Int)
    fun navigateToDoctorProfile()
    fun navigateToNotifications()
    fun navigateToMedicalRecords()
    fun navigateToPrescriptions()
    fun navigateToVaccines()
}
