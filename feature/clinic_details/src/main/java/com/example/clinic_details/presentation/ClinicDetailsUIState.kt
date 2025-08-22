package com.example.clinic_details.presentation

import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class ClinicDetailsUIState(
    val clinicId: Int,
    val type : ClinicDetailsType,
    val clinic: ClinicFullData? = null,
    val screenState: ScreenState = ScreenState.IDLE,
    val sendRequestState: BottomBarState = BottomBarState.DISABLED,
    val toastMessage: UiText? = null,
    val isRefreshing: Boolean = false,
    val isInfoDialogShown: Boolean = false,
    val infoDialogTitle: String = "",
    val infoDialogSubtitle: String = "",
    val isWarningDialogShown: Boolean = false,
    val deactivationReason: String = "",
    val isLoadingDialogShown: Boolean = false,
    val isValidInput: Boolean? = null,
)