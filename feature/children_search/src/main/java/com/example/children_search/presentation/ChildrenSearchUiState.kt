package com.example.children_search.presentation

import com.example.model.enums.ScreenState

data class ChildrenSearchUiState(
    val state: ScreenState = ScreenState.IDLE,
    val query: String = ""
)
