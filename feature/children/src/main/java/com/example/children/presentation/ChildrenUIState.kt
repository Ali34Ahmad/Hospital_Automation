package com.example.children.presentation

import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState

data class ChildrenUIState(
    val guardianId: Int,
    val userChildren: List<ChildFullData> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
)