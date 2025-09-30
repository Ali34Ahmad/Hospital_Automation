package com.example.doctor_profile.presentation

import com.example.doctor_profile.navigation.DoctorProfileAccessType
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.Error

data class DoctorProfileUiState(
    val doctorInfo: DoctorProfileResponse? = null,
    val doctorId: Int? = null,
    val doctorProfileAccessType: DoctorProfileAccessType? = null,
    val profileScreenState: ScreenState = ScreenState.IDLE,

    val isLoggedOutSuccessfully: Boolean = false,
    val logOutError: Error? = null,

    val isAccountDeactivatedSuccessfully: Boolean = false,
    val accountDeactivationError: Error? = null,

    val showLoadingDialog: Boolean = false,
    val loadingDialogText: UiText? = null,

    val showErrorDialog: Boolean = false,
    val errorDialogText: UiText? = null,

    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,

    val selectedAppointmentTypeIndex: Int? = null,
)
