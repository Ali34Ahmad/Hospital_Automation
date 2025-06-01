package com.example.children_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.source.childrenSearch.ChildrenSearchPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.use_cases.children.GetChildrenByNameUseCase
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

class ChildrenSearchViewModel(
    private val getChildrenByNameUseCase: GetChildrenByNameUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<ChildrenSearchUiState>(ChildrenSearchUiState())
    val uiState: StateFlow<ChildrenSearchUiState> = _uiState

    private var _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val childrenFlow = _uiState.map { it.query }
        .debounce(DurationConstants.SEARCH_DEBOUNCE_DELAY)
        .distinctUntilChanged()
        .filter{ it.isNotBlank() }
        .flatMapLatest{ query ->
            onAction(ChildrenSearchUIActions.OnQueryChanged(query))
                Pager(
                    config = PagingConfig(
                        pageSize = PagingConstants.PAGE_SIZE,
                    ),
                    pagingSourceFactory = {
                        ChildrenSearchPagingSource(
                            getChildrenByName = getChildrenByNameUseCase,
                            query = query,
                        )
                    }
                ).flow
        }
        .cachedIn(viewModelScope)


    fun onAction(action: ChildrenSearchUIActions){
        when(action){
            ChildrenSearchUIActions.DeleteQuery -> {
                _uiState.value = _uiState.value.copy(query = "")
            }
            is ChildrenSearchUIActions.OnQueryChanged -> {
                _uiState.value = _uiState.value.copy(query = action.newQuery)
            }
            is ChildrenSearchUIActions.UpdateState ->{
                _uiState.value = _uiState.value.copy(state = action.newState)
            }
            ChildrenSearchUIActions.NavigateBack -> Unit
            is ChildrenSearchUIActions.NavigateToChildDetail -> Unit
        }
    }
}