package com.example.doctor_schedule.presentation

import com.example.model.doctor.appointment.AppointmentState
import com.example.model.enums.ScreenState
import java.time.LocalDate

sealed interface ScheduleUIAction {
    data class UpdateTab(val selectedTab: AppointmentState): ScheduleUIAction
    data class UpdateState(val newState: ScreenState): ScheduleUIAction
    data class UpdateSearchQuery(val newValue: String): ScheduleUIAction
    data class UpdateDate(val newDate: LocalDate?): ScheduleUIAction
    object Refresh : ScheduleUIAction
    object ShowSearchBar: ScheduleUIAction
    object HideSearchBar : ScheduleUIAction
    object ShowDatePicker: ScheduleUIAction
    object HideDatePicker : ScheduleUIAction
    object ToggleDrawer : ScheduleUIAction
    object ToggleTheme: ScheduleUIAction
    object RefreshPermission: ScheduleUIAction
    object ClearDateFilter: ScheduleUIAction
    object UpdateIsFirstLaunchToFalse : ScheduleUIAction
}

interface ScheduleNavigationActions{
    fun navigateToAppointmentDetails(doctorId: Int)
    fun navigateToDoctorProfile()
    fun navigateToDoctorProfile(id:Int)
    fun navigateToChildProfile(id: Int)
    fun navigateToUserProfileProfile(id: Int)
    fun navigateToNotifications()
    fun navigateToMedicalRecords()
    fun navigateToPrescriptions()
    fun navigateToVaccines()
    fun navigateToVaccineTable()
    fun navigateUp()
}
