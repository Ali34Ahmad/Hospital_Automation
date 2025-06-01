package com.example.children_search.presentation

import com.example.model.enums.FetchingDataState

data class ChildrenSearchUiState(
    val state: FetchingDataState = FetchingDataState.IDLE,
    val query: String = ""
)
