package com.example.doctor_schedule.presentation

import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.enums.ScreenState
import java.time.LocalDate

data class ScheduleUIState(
    val hasAdminAccess: Boolean,
    val id: Int?,
    val searchType: AppointmentSearchType,
    val name: String?,
    val speciality: String?,
    val imageUrl: String?,
    val isRefreshing: Boolean = false,
    val screenState: ScreenState = ScreenState.IDLE,
    val askForPermissions: Boolean,
    val permissionsState: ScreenState = if(askForPermissions) ScreenState.LOADING else ScreenState.SUCCESS,
    val selectedTab: AppointmentState = AppointmentState.UPCOMMING,
    val isSearchBarVisible: Boolean = false,
    val isDatePickerVisible: Boolean = false,
    val selectedDate: LocalDate? = null,
    val isDrawerOpened: Boolean = false,
    val searchQuery: String = "",
    val statistics: AppointmentsStatisticsData = AppointmentsStatisticsData(),
    val isDarkTheme: Boolean = false,
    val isPermissionGranted: Boolean = false,
    val isFirstLaunch: Boolean = true
){
    fun hasDateFilter() = selectedDate !=null

    // if we are looking for appointments that are related to someone
    val showProfileInfo : Boolean
        get() = searchType !== AppointmentSearchType.DOCTOR

    val canNavigateUp : Boolean
        get() = name != null
}

