package com.example.prescriptions.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.use_cases.prescription.GetPrescriptionsUseCase
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.model.prescription.PrescriptionWithUser
import com.example.model.user.UserMainInfo
import com.example.prescriptions.navigation.PrescriptionsRoute
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

class PrescriptionsViewModel(
    private val getPrescriptionUseCase: GetPrescriptionsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        PrescriptionsUiState(
            patientId = savedStateHandle.toRoute<PrescriptionsRoute>().patientId,
            childId = savedStateHandle.toRoute<PrescriptionsRoute>().childId,
            doctorId = savedStateHandle.toRoute<PrescriptionsRoute>().doctorId,
        )
    )
    val uiState: StateFlow<PrescriptionsUiState> = _uiState.asStateFlow()

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)
    private val queryFlow = uiState.map { it.searchText }.distinctUntilChanged()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val prescriptionsFlow: Flow<PagingData<PrescriptionWithUser>> = combine(
        queryFlow, refreshTrigger.onStart { emit(Unit) }
    ) { query, _ ->
        PrescriptionsFilter(
            query = query,
        )
    }
        .debounce(500)
        .flatMapLatest { filter ->
            val currentPatientId = uiState.value.patientId
            val currentChildId = uiState.value.childId
            val doctorId = uiState.value.doctorId
            updateIsRefreshing(false)
            Log.d("ChildId",currentChildId.toString())
            loadData(
                onMainUserInfoChanged = { userMainInfo ->
                    updateUserMainInfo(userMainInfo)
                },
                patientId = currentPatientId,
                childId = currentChildId,
                doctorId = doctorId,
                searchText = filter.query
            )
        }
        .cachedIn(viewModelScope)

    suspend fun loadData(
        onMainUserInfoChanged: (UserMainInfo) -> Unit,
        patientId: Int?,
        childId: Int?,
        doctorId: Int?,
        searchText: String?,
    ): Flow<PagingData<PrescriptionWithUser>> {
        val name = if (
            searchText?.isNotBlank() == true &&
            searchText.isNotEmpty() == true
        ) searchText
        else null
        return getPrescriptionUseCase(
            onMainUserInfoChanged = onMainUserInfoChanged,
            patientId = patientId,
            childId = childId,
            doctorId = doctorId,
            name = name,
        )
    }

    private fun updateUserMainInfo(userMainInfo: UserMainInfo) {
        _uiState.update { it.copy(userMainInfo = userMainInfo) }
    }

    fun getUiActions(
        navActions: PrescriptionsNavigationUiActions,
    ): PrescriptionsUiActions = PrescriptionsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): PrescriptionsBusinessUiActions =
        object : PrescriptionsBusinessUiActions {
            override fun onUpdateSearchText(searchText: String) {
                updateSearchText(searchText)
            }

            override fun onDeleteQuery() {
                updateSearchText("")
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

    private fun updateSearchText(searchText: String) {
        _uiState.update { it.copy(searchText = searchText) }
    }

    private fun showSearchBar() {
        _uiState.value = _uiState.value.copy(topBarMode = TopBarState.SEARCH)
    }

    private fun hideSearchBar() {
        _uiState.value = _uiState.value.copy(topBarMode = TopBarState.DEFAULT)
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

data class PrescriptionsFilter(
    val query: String,
)