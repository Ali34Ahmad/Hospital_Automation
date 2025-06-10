package com.example.guardians_search.presentation

import com.example.model.enums.ScreenState

sealed interface GuardiansSearchActions {
    data class OnQueryChange(val query: String) : GuardiansSearchActions
    data class UpdateFetchingDataState(val newState: ScreenState): GuardiansSearchActions
    object OnDeleteQuery: GuardiansSearchActions
    data class UpdateRefreshState(val isRefreshing: Boolean): GuardiansSearchActions
    data object Refresh : GuardiansSearchActions
}

interface GuardiansSearchNavigationActions{
    fun navigateUp()
    fun navigateToGuardianDetails(guardianId: Int,childId: Int?)
}