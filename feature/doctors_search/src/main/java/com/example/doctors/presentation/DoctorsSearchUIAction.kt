package com.example.doctors.presentation

import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState

sealed interface DoctorsSearchUIAction {
    object Refresh: DoctorsSearchUIAction
    data class UpdateSearchQuery(val newValue: String): DoctorsSearchUIAction
    data class UpdateTab(val newTab: EmployeeState): DoctorsSearchUIAction
    data class UpdateScreenState(val newState: ScreenState): DoctorsSearchUIAction
    object ToggleTheme: DoctorsSearchUIAction
    object ToggleDrawer : DoctorsSearchUIAction
    object ToggleTopBarState: DoctorsSearchUIAction
}

interface DoctorsSearchNavigationActions{
    fun navigateUp()
    fun navigateToDoctorProfile(doctorId: Int)
    fun navigateToAdminProfile()
    fun navigateToVaccines()
    fun navigateToNotifications()
    fun navigateToPrescriptions()
    fun navigateToMedicalRecords()
    fun navigateToVaccineTable()
}