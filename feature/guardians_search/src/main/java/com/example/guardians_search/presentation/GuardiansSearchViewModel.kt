package com.example.guardians_search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.example.domain.use_cases.users.paged.SearchForGuardiansByNameUseCase
import com.example.guardians_search.navigation.GuardiansSearchRoute
import com.example.model.enums.ScreenState
import com.example.utility.constants.DurationConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class GuardiansSearchViewModel (
    private val searchForGuardiansByNameUseCase: SearchForGuardiansByNameUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow<GuardiansSearchUiState>(
        GuardiansSearchUiState(
            childId = savedStateHandle.toRoute<GuardiansSearchRoute>().childId
        )
    )
    val uiState: StateFlow<GuardiansSearchUiState> = _uiState


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val guardiansFlow = _uiState
        .map { it.searchQuery }
        .debounce(DurationConstants.SEARCH_DEBOUNCE_DELAY)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            searchForGuardiansByNameUseCase(query)
        }
        .cachedIn(viewModelScope)


    fun onAction(action: GuardiansSearchActions){
        when(action){
            is GuardiansSearchActions.OnQueryChange -> {
                if(action.query.isBlank())
                    _uiState.value = _uiState.value.copy(screenState = ScreenState.IDLE)
                _uiState.value = _uiState.value.copy(searchQuery = action.query)
            }
            GuardiansSearchActions.OnDeleteQuery -> {
                _uiState.value = _uiState.value.copy(searchQuery = "")
                _uiState.value = _uiState.value.copy(screenState = ScreenState.IDLE)
            }
            is GuardiansSearchActions.UpdateFetchingDataState -> {
                _uiState.value = _uiState.value.copy(screenState = action.newState)
            }
        }
    }

}