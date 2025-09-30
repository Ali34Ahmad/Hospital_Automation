package com.example.vaccines.presentation

import com.example.model.enums.Role
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class VaccinesUiState(
    val accessedByRole: Role?=null,
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,
)
