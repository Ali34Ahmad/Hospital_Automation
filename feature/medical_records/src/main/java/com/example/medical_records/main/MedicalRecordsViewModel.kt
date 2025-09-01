package com.example.medical_records.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.use_cases.medical_record.GetAllMedicalRecordsUseCase
import com.example.medical_records.navigation.MedicalRecordsRoute
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.model.medical_record.MedicalRecord
import com.example.model.user.UserMainInfo
import com.example.util.UiText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MedicalRecordsViewModel(
    private val getAllMedicalRecordsUseCase: GetAllMedicalRecordsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        MedicalRecordsUiState(
            doctorId = savedStateHandle.toRoute<MedicalRecordsRoute>().doctorId
        )
    )
    val uiState: StateFlow<MedicalRecordsUiState> = _uiState.asStateFlow()

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)
    private val queryFlow = uiState.map { it.searchText }.distinctUntilChanged()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val medicalRecordsFlow: Flow<PagingData<MedicalRecord>> = combine(
        queryFlow,
        refreshTrigger.onStart { emit(Unit) }
    ) { query, _ ->
        query
    }
        .debounce(500)
        .flatMapLatest { queryText ->
            updateIsRefreshing(false)
            loadData(
                onMainUserInfoChanged = { userMainInfo ->
                    updateUserMainInfo(userMainInfo)
                },
                searchText = queryText
            )
        }
        .cachedIn(viewModelScope)

    suspend fun loadData(
        onMainUserInfoChanged: (UserMainInfo) -> Unit,
        searchText: String?,
    ): Flow<PagingData<MedicalRecord>> {
        val name = if (
            searchText?.isNotBlank() == true &&
            searchText.isNotEmpty() == true
        ) searchText
        else null
        return getAllMedicalRecordsUseCase(
            onMainUserInfoChanged = onMainUserInfoChanged,
            name = name,
            doctorId = uiState.value.doctorId
        )
    }

    fun getUiActions(
        navActions: MedicalRecordsNavigationUiActions,
    ): MedicalRecordsUiActions = MedicalRecordsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): MedicalRecordsBusinessUiActions =
        object : MedicalRecordsBusinessUiActions {
            override fun onUpdateSearchText(searchText: String) {
                updateSearchText(searchText)
            }

            override fun onDeleteQuery() {
                updateSearchText("")
            }

            override fun onChangeToolBarMode(topBarMode: TopBarState) {
                changeToolBarMode(topBarMode)
            }

            override fun onRefresh() {
                viewModelScope.launch {
                    updateIsRefreshing(true)
                    refreshTrigger.emit(Unit)
                }
            }

            override fun onUpdateScreenState(screenState: ScreenState) {
                updateScreenState(screenState)
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

            override fun onShowSearchBar() {
                showSearchBar()
            }

            override fun onHideSearchBar() {
                hideSearchBar()
            }
        }

    private fun showSearchBar() {
        _uiState.value = _uiState.value.copy(topBarMode = TopBarState.SEARCH)
    }

    private fun hideSearchBar() {
        _uiState.value = _uiState.value.copy(topBarMode = TopBarState.DEFAULT)
    }

    private fun updateUserMainInfo(userMainInfo: UserMainInfo) {
        _uiState.update { it.copy(userMainInfo = userMainInfo) }
    }

    private fun updateSearchText(searchText: String) {
        _uiState.update { it.copy(searchText = searchText) }
    }

    private fun changeToolBarMode(topBarMode: TopBarState) {
        _uiState.update { it.copy(topBarMode = topBarMode) }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(screenState = screenState) }
    }

    private fun updateToastMessage(toastMessage: UiText?) {
        _uiState.update { it.copy(toastMessage = toastMessage) }
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

}