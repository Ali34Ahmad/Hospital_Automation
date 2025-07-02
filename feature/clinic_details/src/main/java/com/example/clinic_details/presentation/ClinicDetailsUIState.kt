package com.example.clinic_details.presentation

import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class ClinicDetailsUIState(
    val doctorId : Int,
    val clinicId: Int,
    val clinic: ClinicFullData? = null,
    val screenState: ScreenState = ScreenState.IDLE,
    val sendRequestState: BottomBarState = BottomBarState.DISABLED,
    val toastMessage: UiText? = null,
    val isRefreshing: Boolean = false,
    val isDialogShown: Boolean = false,
    val dialogTitle: String = "",
    val dialogSubtitle: String = ""
)
