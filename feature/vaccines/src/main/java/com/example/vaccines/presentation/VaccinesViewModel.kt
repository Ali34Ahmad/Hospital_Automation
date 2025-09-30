package com.example.vaccines.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.use_cases.vaccine.GetAllVaccinesUseCase
import com.example.model.enums.ScreenState
import com.example.model.vaccine.VaccineData
import com.example.util.UiText
import com.example.vaccines.navigation.VaccinesRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VaccinesViewModel(
    private val getAllVaccinesUseCase: GetAllVaccinesUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(VaccinesUiState(
        accessedByRole = savedStateHandle.toRoute<VaccinesRoute>().accessedByRole
    ))
    val uiState: StateFlow<VaccinesUiState> = _uiState.asStateFlow()

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val vaccinesFlow: Flow<PagingData<VaccineData>> = combine(
        refreshTrigger.onStart { emit(Unit) }
    ) {

    }.flatMapLatest {
        updateIsRefreshing(false)
        getAllVaccinesUseCase()
    }
        .cachedIn(viewModelScope)


    fun getUiActions(
        navActions: VaccinesNavigationUiActions,
    ): VaccinesUiActions = VaccinesUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): VaccinesBusinessUiActions =
        object : VaccinesBusinessUiActions {
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