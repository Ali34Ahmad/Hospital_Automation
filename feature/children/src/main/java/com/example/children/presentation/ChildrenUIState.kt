package com.example.children.presentation

import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.model.helper.IdType

data class ChildrenUIState(
    val userChildren: List<ChildFullData> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val type: IdType
)