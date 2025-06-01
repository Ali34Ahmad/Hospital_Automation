package com.example.children_search.presentation

import com.example.model.enums.FetchingDataState

sealed interface ChildrenSearchUIActions {
    // navigation action
    object NavigateBack: ChildrenSearchUIActions
    data class NavigateToChildDetail(val id: Int): ChildrenSearchUIActions
    //ui action
    data class OnQueryChanged(val newQuery: String): ChildrenSearchUIActions
    object DeleteQuery: ChildrenSearchUIActions
    data class UpdateState(val newState: FetchingDataState): ChildrenSearchUIActions
}