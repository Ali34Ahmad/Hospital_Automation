package com.example.children_search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.children_search.navigation.SearchType
import com.example.domain.use_cases.children.paged.SearchForChildrenAddedByEmployeeByNameUseCase
import com.example.domain.use_cases.children.paged.SearchForChildrenByNameUseCase
import com.example.model.enums.ScreenState
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
    private val searchForChildrenByName: SearchForChildrenByNameUseCase,
    private val searchForChildrenAddedByEmployeeByName: SearchForChildrenAddedByEmployeeByNameUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow<ChildrenSearchUiState>(ChildrenSearchUiState(
    //savedStateHandle.toRoute<ChildrenSearchRoute>().searchType
        searchType = SearchType.EMPLOYEE
    ))
    val uiState: StateFlow<ChildrenSearchUiState> = _uiState


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val childrenFlow = _uiState.map { it.query }
        .filter{ it.isNotBlank() }
        .debounce(DurationConstants.SEARCH_DEBOUNCE_DELAY)
        .distinctUntilChanged()
        .flatMapLatest{ query ->
            getPagingSourceFactory(query)
        }
        .cachedIn(viewModelScope)


    fun onAction(action: ChildrenSearchUIActions){
        when(action){
            ChildrenSearchUIActions.DeleteQuery -> {
                _uiState.value = _uiState.value.copy(query = "")
            }
            is ChildrenSearchUIActions.OnQueryChanged -> {
                if(action.newQuery.isBlank())
                    _uiState.value = _uiState.value.copy(state = ScreenState.IDLE)
                _uiState.value = _uiState.value.copy(query = action.newQuery)
            }
            is ChildrenSearchUIActions.UpdateState ->{
                _uiState.value = _uiState.value.copy(state = action.newState)
            }
            ChildrenSearchUIActions.NavigateBack -> Unit
            is ChildrenSearchUIActions.NavigateToChildDetail -> Unit
        }
    }
    private suspend fun getPagingSourceFactory(query: String)=
        when(_uiState.value.searchType){
            SearchType.GLOBAL ->{
                searchForChildrenByName(query)
            }
            SearchType.EMPLOYEE -> {
                searchForChildrenAddedByEmployeeByName(query)
            }
        }
}