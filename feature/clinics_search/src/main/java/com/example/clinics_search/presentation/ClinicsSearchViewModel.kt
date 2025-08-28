package com.example.clinics_search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.example.clinics_search.navigation.ClinicsSearchRoute
import com.example.domain.use_cases.admin.clinic.GetFilteredClinicsFlowUseCase
import com.example.domain.use_cases.doctor.clinic.GetClinicsFlowUseCase
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClinicsSearchViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getClinics: GetClinicsFlowUseCase,
    private val getUserPreferences: GetUserPreferencesUseCase,
    private val updateIsDarkTheme: UpdateIsDarkThemeUseCase,
    private val getFilteredClinicsFlowUseCase: GetFilteredClinicsFlowUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(
        ClinicsSearchUIState(
            hasAdminAccess = savedStateHandle.toRoute<ClinicsSearchRoute>().hasAdminAccess
        )
    )
    val uiState : StateFlow<ClinicsSearchUIState> = _uiState
        .onStart {
            readTheme()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            _uiState.value
        )

    val refreshTrigger = MutableSharedFlow<Unit>()
    @OptIn(ExperimentalCoroutinesApi::class)
    val clinics = combine(
        uiState.map { it.searchQuery }.distinctUntilChanged(),
        _uiState.map { it.selectedTab }.distinctUntilChanged(),
        refreshTrigger.onStart { emit(Unit) }
    ) {query,tab,_->
            Pair(query,tab)
    }.flatMapLatest { filters->
        val result = if(uiState.value.hasAdminAccess) getFilteredClinicsFlowUseCase(
                query = filters.first,
                status = filters.second,
                onStatisticsUpdated = {
                    updateStatistics(it)
                }
            )else
                getClinics(filters.first)
        updateRefreshState(false)
        result
    }.cachedIn(viewModelScope)

    fun onAction(action: ClinicsSearchUIAction){
        when(action){
            is ClinicsSearchUIAction.GoToSelectionStep -> {
                _uiState.value = _uiState.value.copy(screenStep = ScreenStep.SELECTION)
                refreshData()
            }
            is ClinicsSearchUIAction.UpdateTopBarMode->{
                _uiState.value = _uiState.value.copy(topBarMode = action.state)
            }
            is ClinicsSearchUIAction.UpdateQuery -> {
                _uiState.value = _uiState.value.copy(searchQuery = action.newQuery)
            }
            ClinicsSearchUIAction.Refresh -> {
                updateRefreshState(true)
                refreshData()
            }
            is ClinicsSearchUIAction.UpdateScreenState -> {
                _uiState.value = _uiState.value.copy(screenState = action.newState)
            }
            ClinicsSearchUIAction.ToggleTheme -> viewModelScope.launch{
                updateIsDarkTheme(!_uiState.value.isDarkTheme)
            }
            ClinicsSearchUIAction.ToggleDrawer -> {
                _uiState.value = _uiState.value.copy(
                    isDrawerOpened = !uiState.value.isDrawerOpened
                )
            }
            is ClinicsSearchUIAction.UpdateTab -> updateTab(action.newTab)

        }
    }
    private fun updateTheme(isDarkTheme: Boolean){
        _uiState.value = _uiState.value.copy(isDarkTheme = isDarkTheme)
    }
    private fun updateStatistics(statistics: DepartmentStatistics){
        _uiState.value = _uiState.value.copy(statistics = statistics)
    }
    private fun refreshData()=viewModelScope.launch {
        refreshTrigger.emit(Unit)
    }
    private fun updateRefreshState(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun readTheme() {
        viewModelScope.launch {
            getUserPreferences().collect { userPreference ->
                updateTheme(userPreference.isDarkTheme)
            }
        }
    }
    private fun updateTab(newTab: DepartmentState){
        _uiState.value = _uiState.value.copy(selectedTab = newTab)
    }
}