package com.example.guardians_search.presentation

import com.example.model.enums.ScreenState

sealed interface GuardiansSearchActions {
    data class OnQueryChange(val query: String) : GuardiansSearchActions
    data class UpdateFetchingDataState(val newState: ScreenState): GuardiansSearchActions
    object OnDeleteQuery: GuardiansSearchActions
}

interface GuardiansSearchNavigationActions{
    fun navigateUp()
    fun navigateToGuardianDetails(guardianId: Int,childId: Int?)
}