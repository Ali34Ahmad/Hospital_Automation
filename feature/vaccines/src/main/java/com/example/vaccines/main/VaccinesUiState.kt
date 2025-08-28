package com.example.vaccines.main

import com.example.model.enums.ScreenState
import com.example.model.vaccine.VaccineData
import com.example.util.UiText

data class VaccinesUiState(
//    val vaccines: List<VaccineData>? =null,

    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,
)
