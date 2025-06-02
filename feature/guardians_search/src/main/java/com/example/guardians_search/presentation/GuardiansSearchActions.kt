package com.example.guardians_search.presentation

import com.example.model.enums.ScreenState

sealed interface GuardiansSearchActions {
    object OnNavigateBack : GuardiansSearchActions
    data class NavigateToGuardianDetails(val guardianId: Int,val childId: Int?) : GuardiansSearchActions
    data class OnQueryChange(val query: String) : GuardiansSearchActions
    data class UpdateFetchingDataState(val newState: ScreenState): GuardiansSearchActions
    object OnDeleteQuery: GuardiansSearchActions
}