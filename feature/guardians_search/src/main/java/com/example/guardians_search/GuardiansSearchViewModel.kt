package com.example.guardians_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.source.GuardiansPagingSource
import com.example.domain.use_cases.users.GetGuardiansByNameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class GuardiansSearchViewModel (
    private val getGuardiansByNameUseCase: GetGuardiansByNameUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<GuardiansSearchUiState>(GuardiansSearchUiState())
    val uiState: StateFlow<GuardiansSearchUiState> = _uiState.asStateFlow()

    private var _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val guardiansFlow = searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .filter{ it.isNotBlank() }
        .flatMapLatest { query ->
            onAction(GuardiansSearchActions.HideError)
            if(query.isBlank()){
                flowOf(PagingData.empty())
            }else{
                Pager(
                    config = PagingConfig(
                        pageSize = 50,
                        prefetchDistance = 10
                    ),
                    pagingSourceFactory = {
                        GuardiansPagingSource(
                            getGuardiansByName = getGuardiansByNameUseCase,
                            query = query,
                        )
                    }
                ).flow
            }

        }
        .cachedIn(viewModelScope)


    fun onAction(action: GuardiansSearchActions){
        when(action){
            is GuardiansSearchActions.OnQueryChange -> {
                _searchQuery.value = action.query
            }
            is GuardiansSearchActions.OnSearch -> {
                _uiState.value = _uiState.value.copy(isSearching = action.isSearch)
            }

            GuardiansSearchActions.HideError -> {
                _uiState.value = _uiState.value.copy(hasError = false)
            }
            GuardiansSearchActions.ShowError ->{
                _uiState.value = _uiState.value.copy(hasError = true)
            }

            GuardiansSearchActions.OnDeleteQuery -> {
                _searchQuery.value = ""
            }

            is GuardiansSearchActions.NavigateToGuardianDetails -> TODO()
            GuardiansSearchActions.OnNavigateBack -> TODO()
        }
    }
}