package com.example.appointment_details.presentation

import com.example.model.doctor.appointment.AppointmentData
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class AppointmentDetailsUIState(
    val canEdit: Boolean,
    val appointmentId: Int,
    val appointment: AppointmentData? = null,
    val screenState: ScreenState = ScreenState.IDLE,
    val markAsPassedButtonState: BottomBarState = BottomBarState.IDLE,
    val markAsMissedButtonState: BottomBarState = BottomBarState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,
    val isDialogShown: Boolean = false,
    val dialogTitle: String ="",
    val dialogSubtitle: String = "",
    val isFirstLaunch: Boolean = true
)