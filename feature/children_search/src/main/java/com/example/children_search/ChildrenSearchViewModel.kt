package com.example.children_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.source.ChildrenPagingSource
import com.example.domain.model.constants.SourcesConstants
import com.example.domain.use_cases.children.GetChildrenByNameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class ChildrenSearchViewModel(
    private val getChildrenByNameUseCase: GetChildrenByNameUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<ChildrenSearchUiState>(ChildrenSearchUiState())
    val uiState: StateFlow<ChildrenSearchUiState> = _uiState

    private var _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val childrenFlow = searchQuery
        .debounce(150)
        .distinctUntilChanged()
        .filter{ it.isNotBlank() }
        .flatMapLatest{ query ->
            onAction(ChildrenSearchUIActions.HideError)
            if(query.isBlank()){
                flowOf(PagingData.empty())
            }else{
                Pager(
                    config = PagingConfig(
                        pageSize = SourcesConstants.PAGE_SIZE,
                    ),
                    pagingSourceFactory = {
                        ChildrenPagingSource(
                            getChildrenByName = getChildrenByNameUseCase,
                            query = query,
                        )
                    }
                ).flow
            }
        }
        .cachedIn(viewModelScope)


    fun onAction(action: ChildrenSearchUIActions){
        when(action){

            ChildrenSearchUIActions.DeleteQuery -> {
                _searchQuery.value = ""
            }
            ChildrenSearchUIActions.HideError -> {
                _uiState.value = _uiState.value.copy(hasError = false)
            }
            is ChildrenSearchUIActions.OnSearchQueryChanged -> {
                _searchQuery.value = action.newQuery
            }
            ChildrenSearchUIActions.ShowError -> {
                _uiState.value = _uiState.value.copy(hasError = true)
            }
            is ChildrenSearchUIActions.OnSearch -> {
                _uiState.value = _uiState.value.copy(isSearching = action.isSearching)
            }
            ChildrenSearchUIActions.NavigateBack -> TODO()
            is ChildrenSearchUIActions.NavigateToChildDetail -> TODO()
        }
    }
}