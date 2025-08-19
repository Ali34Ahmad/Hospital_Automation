package com.example.medical_prescriptions.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.use_cases.vaccine.GetAllPrescriptionsUseCase
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.model.prescription.PrescriptionWithUser
import com.example.util.UiText
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

class MedicalPrescriptionsViewModel(
    private val getMedicalPrescriptionUseCase: GetAllPrescriptionsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MedicalPrescriptionsUiState())
    val uiState: StateFlow<MedicalPrescriptionsUiState> = _uiState.asStateFlow()

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val prescriptionsFlow: Flow<PagingData<PrescriptionWithUser>> = combine(
        refreshTrigger.onStart { emit(Unit) }
    ) {

    }.flatMapLatest {
        updateIsRefreshing(false)
        getMedicalPrescriptionUseCase()
    }
        .cachedIn(viewModelScope)


    fun getUiActions(
        navActions: MedicalPrescriptionsNavigationUiActions,
    ): MedicalPrescriptionsUiActions = MedicalPrescriptionsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): MedicalPrescriptionsBusinessUiActions =
        object : MedicalPrescriptionsBusinessUiActions {
            override fun onUpdateSearchText(searchText: String) {
                updateSearchText(searchText)
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