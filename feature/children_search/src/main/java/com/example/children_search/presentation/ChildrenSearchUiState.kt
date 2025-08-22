package com.example.children_search.presentation

import com.example.children_search.navigation.SearchType
import com.example.model.enums.ScreenState

data class ChildrenSearchUiState(
    val employeeId: Int? = null,
    val searchType: SearchType = SearchType.GLOBAL,
    val state: ScreenState = ScreenState.IDLE,
    val query: String = "",
    val isRefreshing: Boolean = false,
)
