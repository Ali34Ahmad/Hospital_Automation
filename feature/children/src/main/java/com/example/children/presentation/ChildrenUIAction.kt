package com.example.children.presentation

import com.example.model.enums.ScreenState
import com.example.util.UiText


sealed interface ChildrenUIAction {
    object Retry: ChildrenUIAction
    data class UpdateScreenState(val newState: ScreenState): ChildrenUIAction
    data class UpdateRefreshState(val isRefreshing: Boolean): ChildrenUIAction
    data object Refresh : ChildrenUIAction
    data class ShowToast(val message: UiText): ChildrenUIAction
}

interface ChildrenNavigationAction{
    fun navigateUp()
    fun navigateToChildProfile(childId: Int)
}