package com.example.children_search

sealed interface ChildrenSearchUIActions {
    // navigation action
    object NavigateBack: ChildrenSearchUIActions
    data class NavigateToChildDetail(val id: Int): ChildrenSearchUIActions
    //ui action
    data class OnSearchQueryChanged(val newQuery: String): ChildrenSearchUIActions
    object DeleteQuery: ChildrenSearchUIActions
    object ShowError: ChildrenSearchUIActions
    object HideError : ChildrenSearchUIActions
    data class OnSearch(val isSearching: Boolean): ChildrenSearchUIActions
}