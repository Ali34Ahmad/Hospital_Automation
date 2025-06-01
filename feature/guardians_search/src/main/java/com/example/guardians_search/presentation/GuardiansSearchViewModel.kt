package com.example.guardians_search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.source.GuardiansPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.use_cases.users.GetGuardiansByNameUseCase
import com.example.guardians_search.navigation.GuardiansSearchRoute
import com.example.utility.constants.DurationConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class GuardiansSearchViewModel (
    private val getGuardiansByNameUseCase: GetGuardiansByNameUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow<GuardiansSearchUiState>(
        GuardiansSearchUiState(
            childId = savedStateHandle
                .toRoute<GuardiansSearchRoute>()
                .childId
        )
    )
    val uiState: StateFlow<GuardiansSearchUiState> = _uiState


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val guardiansFlow = _uiState.map { it.searchQuery }
        .debounce(DurationConstants.SEARCH_DEBOUNCE_DELAY)
        .distinctUntilChanged()
        .filter{ it.isNotBlank() }
        .flatMapLatest { query ->
                Pager(
                    config = PagingConfig(
                        pageSize = PagingConstants.PAGE_SIZE,
                    ),
                    pagingSourceFactory = {
                        GuardiansPagingSource(
                            getGuardiansByName = getGuardiansByNameUseCase,
                            query = query,
                        )
                    }
                ).flow
        }
        .cachedIn(viewModelScope)


    fun onAction(action: GuardiansSearchActions){
        when(action){
            is GuardiansSearchActions.OnQueryChange -> {
                _uiState.value = _uiState.value.copy(searchQuery = action.query)
            }
            GuardiansSearchActions.OnDeleteQuery -> {
                _uiState.value = _uiState.value.copy(searchQuery = "")
            }


            is GuardiansSearchActions.UpdateFetchingDataState -> {
                _uiState.value = _uiState.value.copy(fetchingDataState = action.newState)
            }
            is GuardiansSearchActions.NavigateToGuardianDetails -> Unit
            GuardiansSearchActions.OnNavigateBack -> Unit
        }
    }
}