package com.example.doctor_schedule.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.doctor_schedule.presentation.model.AppointmentsFilter
import com.example.doctor_schedule.presentation.model.upcomingMapper
import com.example.domain.use_cases.doctor.appointment.GetAppointmentsFlowUseCase
import com.example.ext.toAppropriateFormat
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.enums.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
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

class DoctorScheduleViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getAppointmentsFlowUseCase: GetAppointmentsFlowUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(DoctorScheduleUIState())
    val uiState : StateFlow<DoctorScheduleUIState> = _uiState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            DoctorScheduleUIState()
        )


    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)
    private val queryFlow = uiState.map { it.searchQuery }.distinctUntilChanged()
    private val selectedTabFlow = uiState.map { it.selectedTab }.distinctUntilChanged()
    private val selectedDateFlow = uiState.map { it.selectedDate }.distinctUntilChanged()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val appointmentsFlow= combine(
        queryFlow,selectedTabFlow,selectedDateFlow,refreshTrigger.onStart { emit(Unit) },
    ) {
        query,selectedTab,selectedDate,_ ->
        AppointmentsFilter(
            tab = selectedTab,
            date = selectedDate,
            query = query
        )
    }
        .debounce(500)
        .flatMapLatest { filter->
            val result = loadData(
                selectedTab = filter.tab,
                onStatisticsChanged = { statistics ->
                    updateStatistics(statistics)
                },
                dateFilter = filter.date?.toAppropriateFormat(),
                queryFilter = filter.query,
            ).upcomingMapper()

            updateRefreshState(false)

            result
        }
        .cachedIn(viewModelScope)

    fun onAction(action: DoctorScheduleUIAction){
        when(action){
            is DoctorScheduleUIAction.UpdateTab ->{
               viewModelScope.launch {
                   _uiState.value = _uiState.value.copy(selectedTab = action.selectedTab)
               }
            }
            is DoctorScheduleUIAction.UpdateState -> {
                updateScreenState(action.newState)
            }
            DoctorScheduleUIAction.Refresh -> {
                viewModelScope.launch {
                    updateRefreshState(true)
                    refreshTrigger.emit(Unit)
                }
            }

            DoctorScheduleUIAction.HideSearchBar -> {
                _uiState.value = _uiState.value.copy(searchQuery = "")
                _uiState.value = _uiState.value.copy(isSearchBarVisible = false)
            }
            DoctorScheduleUIAction.ShowSearchBar -> {
                _uiState.value = _uiState.value.copy(isSearchBarVisible = true)
            }

            DoctorScheduleUIAction.CloseDrawer ->{
                _uiState.value = _uiState.value.copy(isDrawerOpened = false)
            }
            DoctorScheduleUIAction.HideDatePicker ->{
                _uiState.value = _uiState.value.copy(isDatePickerVisible = false)
                Log.d("DoctorScheduleViewModel","is visible : ${_uiState.value.isDatePickerVisible}")
            }
            DoctorScheduleUIAction.OpenDrawer ->{
                _uiState.value = _uiState.value.copy(isDrawerOpened = true)
            }
            DoctorScheduleUIAction.ShowDatePicker -> {
                _uiState.value = _uiState.value.copy(isDatePickerVisible = true)
                Log.d("DoctorScheduleViewModel","is visible : ${_uiState.value.isDatePickerVisible}")
            }
            is DoctorScheduleUIAction.UpdateSearchQuery -> {
                _uiState.value = _uiState.value.copy(searchQuery = action.newValue)
            }

            is DoctorScheduleUIAction.UpdateDate -> {
                _uiState.value = _uiState.value.copy(selectedDate = action.newDate)
            }
        }
    }

    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    private suspend fun loadData(
        selectedTab: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        dateFilter: String?,
        queryFilter: String?
    ) :Flow<PagingData<AppointmentData>> =
        getAppointmentsFlowUseCase(
            appointmentState = selectedTab,
            onStatisticsChanged = onStatisticsChanged,
            dateFilter = dateFilter,
            queryFilter = queryFilter,
        )
    private fun updateRefreshState(isRefreshing: Boolean) {
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun updateStatistics(statistics: AppointmentsStatisticsData){
        _uiState.value = _uiState.value.copy(statistics=statistics)
    }
}