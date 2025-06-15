package com.example.children.presentation

import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.util.UiText

data class ChildrenUIState(
    val guardianId: Int,
    val userChildren: List<ChildFullData> = emptyList(),
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,
)