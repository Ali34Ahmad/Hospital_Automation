package com.example.pharmacies_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.use_cases.admin.pharmacy.GetFilteredPharmaciesFlowUseCase
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
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

class PharmaciesSearchViewModel(
    private val getPharmacies: GetFilteredPharmaciesFlowUseCase,
    private val updateIsDarkThemeUseCase: UpdateIsDarkThemeUseCase,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,

    ): ViewModel() {

    private val _uiState = MutableStateFlow(PharmaciesSearchUIState())
    val uiState: StateFlow<PharmaciesSearchUIState> = _uiState
        .onStart {
            readTheme()
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
    val pharmacies = combine(
        queryFlow,
        selectedTabFlow,
        refreshTrigger.onStart { emit(Unit) }
    ) { query,tab,_->
        Pair(query,tab)
    }.flatMapLatest { filters->
        val result = getPharmacies(
            query = filters.first,
            status = filters.second,
            onStatisticsUpdated = {statistics->
                updateStatistics(statistics)
            }
        )
        updateRefreshState(false)
        result
    }.cachedIn(viewModelScope)

    fun onAction(action: PharmaciesSearchUIAction){
        when(action){
            PharmaciesSearchUIAction.Refresh -> refresh()
            is PharmaciesSearchUIAction.UpdateScreenState -> updateScreenState(action.newState)
            is PharmaciesSearchUIAction.UpdateSearchQuery -> updateSearchQuery(action.newValue)
            is PharmaciesSearchUIAction.UpdateTab -> updateSelectedTab(action.newTab)
            PharmaciesSearchUIAction.ToggleDrawer -> toggleDrawer()
            PharmaciesSearchUIAction.ToggleTheme -> toggleTheme()
        }
    }


    private fun toggleDrawer(){
        _uiState.value = _uiState.value.copy(isDrawerOpened = !uiState.value.isDrawerOpened)
    }
    private fun updateStatistics(statistics: DepartmentStatistics){
        _uiState.value = _uiState.value.copy(statistics=statistics)
    }
    private fun refresh() = viewModelScope.launch {
        updateRefreshState(true)
        refreshTrigger.emit(Unit)
    }
    private fun toggleTheme() = viewModelScope.launch{
        updateIsDarkThemeUseCase(!_uiState.value.isDarkTheme)
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
    private fun updateSelectedTab(newTab: DepartmentState){
        _uiState.value = _uiState.value.copy(selectedTab = newTab)
    }
    private fun updateTheme(isDarkTheme: Boolean) {
        _uiState.value = _uiState.value.copy(isDarkTheme = isDarkTheme)
    }
    private fun readTheme() {
        viewModelScope.launch {
            getUserPreferencesUseCase().collect { userPreference ->
                updateTheme(userPreference.isDarkTheme)
            }
        }
    }

}