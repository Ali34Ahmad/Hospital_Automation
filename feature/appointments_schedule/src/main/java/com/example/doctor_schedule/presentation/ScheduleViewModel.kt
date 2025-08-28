package com.example.doctor_schedule.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.doctor_schedule.navigation.AppointmentSearchType
import com.example.doctor_schedule.presentation.model.AppointmentsFilter
import com.example.doctor_schedule.presentation.model.upcomingMapper
import com.example.domain.use_cases.appointment.GetChildAppointmentsUseCase
import com.example.domain.use_cases.appointment.GetDoctorAppointmentsUseCase
import com.example.domain.use_cases.appointment.GetUserAppointmentsUseCase
import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import com.example.ext.toAppropriateDateFormat
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.enums.Role
import com.example.model.enums.ScreenState
import com.example.model.role_config.RoleAppConfig
import com.example.utility.network.map
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
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
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class ScheduleViewModel(
    private val updateIsDarkThemeUseCase: UpdateIsDarkThemeUseCase,
    private val getDoctorAppointmentsUseCase: GetDoctorAppointmentsUseCase,
    private val getUserAppointmentsUseCase: GetUserAppointmentsUseCase,
    private val getChildAppointmentsUseCase: GetChildAppointmentsUseCase,
    private val checkEmployeePermissionUseCase: CheckEmployeePermissionUseCase,
    private val roleAppConfig:RoleAppConfig,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val doctorId = 143
    private val childId = 1
    private val userId = 128

    private val id = userId
    private val hasAdminAccess = true
    private val targetRole = AppointmentSearchType.USER
    private val name = "Ali Mansoura"
    private val speciality: String? = null
    private val imageUrl : String? = "https://randomuser.me/api/portraits/men/32.jpg"

    private val _uiState = MutableStateFlow(
        ScheduleUIState(
            id = id,
            hasAdminAccess = hasAdminAccess,
            searchType = targetRole,
            name = name,
            speciality = speciality,
            imageUrl = imageUrl
        )
    )

    val uiState : StateFlow<ScheduleUIState> = _uiState
        .onStart {
            readTheme()
            checkPermissions()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _uiState.value
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
                dateFilter = filter.date?.toAppropriateDateFormat(),
                queryFilter = filter.query,
            ).upcomingMapper()
            updateRefreshState(false)
            result
        }
        .cachedIn(viewModelScope)

    fun onAction(action: ScheduleUIAction){
        when(action){
            is ScheduleUIAction.UpdateTab ->updateTab(action.selectedTab)
            is ScheduleUIAction.UpdateState -> updateScreenState(action.newState)
            ScheduleUIAction.Refresh ->refresh()
            ScheduleUIAction.HideSearchBar ->hideSearchBar()
            ScheduleUIAction.ShowSearchBar ->showSearchBar()
            ScheduleUIAction.HideDatePicker ->hideDatePicker()
            ScheduleUIAction.ToggleDrawer -> toggleDrawer()
            ScheduleUIAction.ShowDatePicker -> showDatePicker()
            is ScheduleUIAction.UpdateSearchQuery -> updateSearchQuery(action.newValue)
            is ScheduleUIAction.UpdateDate -> updateDate(action.newDate)
            ScheduleUIAction.ToggleTheme -> toggleTheme()
            ScheduleUIAction.RefreshPermission ->checkPermissions()
            ScheduleUIAction.ClearDateFilter -> clearDateFilter()
            ScheduleUIAction.UpdateIsFirstLaunchToFalse -> updateIsFirstLaunchToFalse()
        }
    }

    private fun updateTab(newTap: AppointmentState){
        _uiState.value = _uiState.value.copy(selectedTab = newTap)
    }

    private fun hideSearchBar() {
        _uiState.value = _uiState.value.copy(isSearchBarVisible = false, searchQuery = "")
    }
    private fun toggleDrawer(){
        _uiState.value = _uiState.value.copy(isDrawerOpened = !uiState.value.isDrawerOpened)
    }
    private fun hideDatePicker(){
        _uiState.value = _uiState.value.copy(isDatePickerVisible = false)
    }
    private fun showSearchBar() {
        _uiState.value = _uiState.value.copy(isSearchBarVisible = true)
    }
    private fun showDatePicker(){
        _uiState.value = _uiState.value.copy(isDatePickerVisible = true)
    }
    private fun updateSearchQuery(newQuery: String){
        _uiState.value = _uiState.value.copy(searchQuery = newQuery)
    }
    private fun updateDate(newDate: LocalDate?){
        _uiState.value = _uiState.value.copy(selectedDate = newDate)
    }
    private fun toggleTheme() = viewModelScope.launch{
        updateIsDarkThemeUseCase(!_uiState.value.isDarkTheme)
    }

    private fun clearDateFilter(){
        _uiState.value = _uiState.value.copy(selectedDate = null)
    }
    private fun refresh() = viewModelScope.launch {
        updateRefreshState(true)
        refreshTrigger.emit(Unit)
    }
    private fun updateIsFirstLaunchToFalse(){
        _uiState.value = _uiState.value.copy(isFirstLaunch = false)
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
        when(uiState.value.searchType){
            AppointmentSearchType.DOCTOR ->
                getDoctorAppointmentsUseCase(
                    appointmentState = selectedTab,
                    onStatisticsChanged = onStatisticsChanged,
                    dateFilter = dateFilter,
                    queryFilter = queryFilter,
                    doctorId = uiState.value.id
                )

            AppointmentSearchType.CHILD ->
                uiState.value.id?.let { childId ->
                    getChildAppointmentsUseCase(
                        appointmentState = selectedTab,
                        onStatisticsChanged = onStatisticsChanged,
                        dateFilter = dateFilter,
                        queryFilter = queryFilter,
                        childId = childId
                    )
                }?: emptyFlow()
            AppointmentSearchType.USER ->
                uiState.value.id?.let { userId ->
                    getUserAppointmentsUseCase(
                        appointmentState = selectedTab,
                        onStatisticsChanged = onStatisticsChanged,
                        dateFilter = dateFilter,
                        queryFilter = queryFilter,
                        userId = userId
                    )
                }?:emptyFlow()
        }



    private fun updateRefreshState(isRefreshing: Boolean) {
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun updateStatistics(statistics: AppointmentsStatisticsData){
        _uiState.value = _uiState.value.copy(statistics =statistics)
    }
    private fun updatePermission(isGranted: Boolean){
        _uiState.value = _uiState.value.copy(isPermissionGranted = isGranted)
    }
    private fun updatePermissionState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(permissionsState = newState)
    }
    private fun updateTheme(isDarkTheme: Boolean) {
        _uiState.value = _uiState.value.copy(isDarkTheme = isDarkTheme)
    }
    /**
     * checks the permissions from the server then store it locally.
     */
    private fun checkPermissions() = viewModelScope.launch{
        val result= checkEmployeePermissionUseCase(roleAppConfig.role)
        result.map { it.permissionGranted }
            .onError {
                Log.e("DoctorViewModel"," Error : $it")
                updatePermissionState(ScreenState.ERROR)
            }.onSuccess {reponse: Boolean->
                Log.d("DoctorViewModel"," Success : $reponse")
                updatePermission(reponse)
                updatePermissionState(ScreenState.SUCCESS)
            }
    }

    /**
     * get the theme that was stored locally.
     */
    private fun readTheme() {
        viewModelScope.launch {
            getUserPreferencesUseCase().collect { userPreference ->
                updateTheme(userPreference.isDarkTheme)
            }
        }
    }
}