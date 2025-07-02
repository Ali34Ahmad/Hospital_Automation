package com.example.clinics_search.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.use_cases.doctor.clinic.GetClinicsFlowUseCase
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ClinicsSearchViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getClinics: GetClinicsFlowUseCase,
    private val getUserPreferences: GetUserPreferencesUseCase,
    private val updateIsDarkTheme: UpdateIsDarkThemeUseCase,
): ViewModel() {
    val id: Int = 122
    private val _uiState = MutableStateFlow(ClinicsSearchUIState(doctorId = id))
    val uiState : StateFlow<ClinicsSearchUIState> = _uiState

    init {
        viewModelScope.launch {
            readTheme()
        }
    }
    val refreshTrigger = MutableSharedFlow<Unit>()
    @OptIn(ExperimentalCoroutinesApi::class)
    val clinicsFlow = combine(
        uiState.map { it.searchQuery }.distinctUntilChanged(),
        refreshTrigger.onStart { emit(Unit) }
    ) {query,_->
            query
    }.flatMapLatest { query->
        val result = getClinics(query)
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
                Log.d("ClinicsViewModel","ScreenState: ${action.newState}")
                _uiState.value = _uiState.value.copy(screenState = action.newState)
            }
            ClinicsSearchUIAction.ToggleTheme -> viewModelScope.launch{
                updateIsDarkTheme(!_uiState.value.isDarkTheme)
            }

        }
    }
    private fun updateTheme(isDarkTheme: Boolean){
        _uiState.value = _uiState.value.copy(isDarkTheme = isDarkTheme)
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
}