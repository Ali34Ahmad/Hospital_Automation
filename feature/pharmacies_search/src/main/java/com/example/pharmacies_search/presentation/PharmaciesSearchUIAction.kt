package com.example.pharmacies_search.presentation

import com.example.model.admin.DepartmentState
import com.example.model.enums.ScreenState

interface PharmaciesSearchUIAction {
    object Refresh: PharmaciesSearchUIAction
    data class UpdateSearchQuery(val newValue: String): PharmaciesSearchUIAction
    data class UpdateTab(val newTab: DepartmentState): PharmaciesSearchUIAction
    data class UpdateScreenState(val newState: ScreenState): PharmaciesSearchUIAction
    object ToggleTheme: PharmaciesSearchUIAction
    object ToggleDrawer : PharmaciesSearchUIAction
}

interface PharmaciesSearchNavigationActions{
    fun navigateToPharmacyDetails(pharmacyId: Int)
    fun navigateToAdminProfile()
    fun navigateToVaccines()
    fun navigateToNotifications()
    fun navigateToPrescriptions()
    fun navigateToMedicalRecords()
    fun navigateToVaccineTable()
}