package com.example.child_profile.presentation

import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class ChildProfileUIState(
    val child: ChildFullData? = null,
    val state: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,
)
