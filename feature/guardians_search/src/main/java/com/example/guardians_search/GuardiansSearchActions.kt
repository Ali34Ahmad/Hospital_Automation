package com.example.guardians_search

sealed interface GuardiansSearchActions {
    object OnNavigateBack : GuardiansSearchActions
    data class NavigateToGuardianDetails(val id: Int) : GuardiansSearchActions
    data class OnQueryChange(val query: String) : GuardiansSearchActions
    data class OnSearch(val isSearch: Boolean) : GuardiansSearchActions
    object ShowError: GuardiansSearchActions
    object HideError : GuardiansSearchActions
    object OnDeleteQuery: GuardiansSearchActions
}