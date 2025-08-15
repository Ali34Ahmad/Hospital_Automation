package com.example.doctors.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.use_cases.admin.doctor.GetDoctorsByClinicUseCase
import com.example.domain.use_cases.admin.doctor.GetDoctorsUseCase
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
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

class DoctorsSearchViewModel(
    private val getDoctorsUseCase: GetDoctorsUseCase,
    private val updateIsDarkThemeUseCase: UpdateIsDarkThemeUseCase,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getDoctorsByClinicUseCase: GetDoctorsByClinicUseCase
    ): ViewModel() {
    val clinicId = 1
    val clinicName = "Department of Digestive"

    private val _uiState = MutableStateFlow(
        DoctorsSearchUIState(
            clinicId = clinicId,
            clinicName = clinicName
        )
    )
    val uiState: StateFlow<DoctorsSearchUIState> = _uiState
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
    val doctors = combine(
        queryFlow,
        selectedTabFlow,
        refreshTrigger.onStart { emit(Unit) }
    ) { query,tab,_->
        Pair(query,tab)
    }.flatMapLatest { filters->
        val result = loadData(
            query = filters.first,
            status = filters.second,
            onStatisticsUpdated = { statistics ->
                updateStatistics(statistics)
            }
        )
        updateRefreshState(false)
        result
    }.cachedIn(viewModelScope)

    fun onAction(action: DoctorsSearchUIAction){
        when(action){
            DoctorsSearchUIAction.Refresh -> refresh()
            is DoctorsSearchUIAction.UpdateScreenState -> updateScreenState(action.newState)
            is DoctorsSearchUIAction.UpdateSearchQuery -> updateSearchQuery(action.newValue)
            is DoctorsSearchUIAction.UpdateTab -> updateSelectedTab(action.newTab)
            DoctorsSearchUIAction.ToggleDrawer -> toggleDrawer()
            DoctorsSearchUIAction.ToggleTheme -> toggleTheme()
            DoctorsSearchUIAction.ToggleTopBarState -> toggleTopBarState()
        }
    }

    private fun toggleTopBarState(){
        val currentState = uiState.value.topBarState
        val newState = if(currentState == TopBarState.DEFAULT) TopBarState.SEARCH
        else TopBarState.DEFAULT

        _uiState.value = _uiState.value.copy(topBarState = newState)
    }
    private fun toggleDrawer(){
        _uiState.value = _uiState.value.copy(isDrawerOpened = !uiState.value.isDrawerOpened)
    }
    private fun updateStatistics(statistics: EmploymentStatistics){
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
    private fun updateSelectedTab(newTab: EmployeeState){
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
    //When passing a clinic id to the doctors search screen
    //then it returns all the doctors by the clinic id
    //else it returns all the doctors in the system.
    suspend fun loadData(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics)-> Unit,
    ) = if (uiState.value.clinicId != null){
        getDoctorsByClinicUseCase(
            query = query,
            status = status,
            onStatisticsUpdated = onStatisticsUpdated,
            clinicId = clinicId
        )
    } else {
        getDoctorsUseCase(
            query = query,
            status = status,
            onStatisticsUpdated = onStatisticsUpdated
        )
    }
}