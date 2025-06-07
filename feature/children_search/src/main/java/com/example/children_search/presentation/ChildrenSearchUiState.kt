package com.example.children_search.presentation

import com.example.children_search.navigation.SearchType
import com.example.model.enums.ScreenState

data class ChildrenSearchUiState(
    val searchType: SearchType = SearchType.GLOBAL,
    val state: ScreenState = ScreenState.IDLE,
    val query: String = ""
)
