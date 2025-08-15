package com.example.employees_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.admin.employee.GetEmployeesUseCase
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmployeeSearchViewModel(
    private val getEmployeesUseCase: GetEmployeesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(EmployeesSearchUIState())
    val uiState : StateFlow<EmployeesSearchUIState> = _uiState
        .onStart {

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            _uiState.value
        )

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)
    @OptIn(FlowPreview::class)
    private val queryFlow = uiState.map { it.searchQuery }.distinctUntilChanged().debounce(500)
    private val selectedTabFlow = uiState.map { it.selectedTab }.distinctUntilChanged()

    @OptIn(ExperimentalCoroutinesApi::class)
    val employees = combine(
        queryFlow,
        selectedTabFlow,
        refreshTrigger.onStart { emit(Unit) }
    ) { query,selectedTab,_->
        Pair(query,selectedTab)
    }.flatMapLatest { filters->
        val result = getEmployeesUseCase(
            query = filters.first,
            status = filters.second,
            onStatisticsUpdated = {statistics->
                updateStatistics(statistics)
            }
        )
        updateRefreshState(false)
        result
    }

    fun onAction(action: EmployeesSearchUIAction){
        when(action){
            EmployeesSearchUIAction.Refresh -> refresh()
            is EmployeesSearchUIAction.UpdateScreenState -> updateScreenState(action.newState)
            is EmployeesSearchUIAction.UpdateSearchQuery -> updateSearchQuery(action.newValue)
            is EmployeesSearchUIAction.UpdateTab -> updateSelectedTab(action.newTab)
        }
    }

    private fun updateStatistics(statistics: EmploymentStatistics){
        _uiState.value = _uiState.value.copy(statistics=statistics)
    }
    private fun refresh() = viewModelScope.launch {
        updateRefreshState(true)
        refreshTrigger.emit(Unit)
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    private fun updateSearchQuery(newValue: String){
        _uiState.value = _uiState.value.copy(searchQuery = newValue)
    }
    private fun updateRefreshState(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun updateSelectedTab(newTab: EmployeeState){
        _uiState.value = _uiState.value.copy(selectedTab = newTab)
    }
}