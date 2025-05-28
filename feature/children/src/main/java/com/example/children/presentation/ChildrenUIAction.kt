package com.example.children.presentation

import com.example.model.enums.FetchingDataState


sealed interface ChildrenUIAction {
    object NavigateUp: ChildrenUIAction
    object Retry: ChildrenUIAction

    data class UpdateFetchingDataState(val newState: FetchingDataState): ChildrenUIAction
    data class NavigateToChildProfile(val childId: Int): ChildrenUIAction
}