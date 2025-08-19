package com.example.medical_records.main

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.util.UiText

data class MedicalRecordsUiState(
    val searchText: String = "",
    val topBarMode: TopBarState = TopBarState.DEFAULT,

    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage: UiText?=null,
)