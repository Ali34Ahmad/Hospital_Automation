package com.example.doctor_schedule.presentation

import com.example.model.doctor.appointment.AppointmentState
import com.example.model.enums.ScreenState
import java.time.LocalDate

sealed interface DoctorScheduleUIAction {
    data class UpdateTab(val selectedTab: AppointmentState): DoctorScheduleUIAction
    data class UpdateState(val newState: ScreenState): DoctorScheduleUIAction
    data class UpdateSearchQuery(val newValue: String): DoctorScheduleUIAction
    data class UpdateDate(val newDate: LocalDate?): DoctorScheduleUIAction
    object Refresh : DoctorScheduleUIAction
    object ShowSearchBar: DoctorScheduleUIAction
    object HideSearchBar : DoctorScheduleUIAction
    object ShowDatePicker: DoctorScheduleUIAction
    object HideDatePicker : DoctorScheduleUIAction
    object ToggleDrawer : DoctorScheduleUIAction
    object ToggleTheme: DoctorScheduleUIAction
    object RefreshPermission: DoctorScheduleUIAction
    object ClearDateFilter: DoctorScheduleUIAction
}

interface DoctorScheduleNavigationActions{
    fun navigateToAppointmentDetails(doctorId: Int)
    fun navigateToDoctorProfile()
    fun navigateToNotifications()
    fun navigateToMedicalRecords()
    fun navigateToPrescriptions()
    fun navigateToVaccines()
}
