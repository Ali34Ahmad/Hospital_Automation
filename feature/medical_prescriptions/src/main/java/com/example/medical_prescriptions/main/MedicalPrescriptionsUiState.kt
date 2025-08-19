package com.example.medical_prescriptions.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.util.UiText

data class MedicalPrescriptionsUiState(
    val searchText: String = "",
    val topBarMode: TopBarState = TopBarState.DEFAULT,

    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage: UiText?=null,
)