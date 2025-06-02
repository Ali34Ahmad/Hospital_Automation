package com.example.children.presentation

import com.example.model.enums.ScreenState


sealed interface ChildrenUIAction {
    object NavigateUp: ChildrenUIAction
    object Retry: ChildrenUIAction

    data class UpdateFetchingDataState(val newState: ScreenState): ChildrenUIAction
    data class NavigateToChildProfile(val childId: Int): ChildrenUIAction
}