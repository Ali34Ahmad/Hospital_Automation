package com.example.children_search.presentation

import com.example.model.enums.ScreenState

sealed interface ChildrenSearchUIActions {
    //ui action
    data class OnQueryChanged(val newQuery: String): ChildrenSearchUIActions
    object DeleteQuery: ChildrenSearchUIActions
    data class UpdateState(val newState: ScreenState): ChildrenSearchUIActions
    data class UpdateRefreshState(val isRefreshing: Boolean): ChildrenSearchUIActions
    data object Refresh : ChildrenSearchUIActions
}
interface ChildrenSearchNavigationActions{
    fun navigateUp()
    fun navigateToChildProfile(childId: Int)
}