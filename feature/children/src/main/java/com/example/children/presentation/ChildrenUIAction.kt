package com.example.children.presentation

import com.example.model.enums.ScreenState


sealed interface ChildrenUIAction {
    object Retry: ChildrenUIAction
    data class UpdateFetchingDataState(val newState: ScreenState): ChildrenUIAction
}

interface ChildrenNavigationAction{
    fun navigateUp()
    fun navigateToChildProfile(childId: Int)
}