package com.example.medicines_search.presentation.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import androidx.paging.cachedIn
import com.example.domain.use_cases.medicine.GetMedicinesFlowUseCase
import com.example.model.enums.TopBarState
import com.example.util.UiText
import com.example.utility.constants.DurationConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MedicineSearchViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getMedicines: GetMedicinesFlowUseCase
): ViewModel() {
    val appointmentId = 1
    val doctorId = 122

    private val _uiState = MutableStateFlow(
        MedicinesSearchUIState(
            doctorId=doctorId,
            appointmentId=appointmentId
        )
    )
    val uiState: StateFlow<MedicinesSearchUIState> = _uiState

    private val queryFlow = _uiState.map { it.query }.distinctUntilChanged()
    private val refreshTrigger = MutableSharedFlow<Unit>()


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val medicinesFlow = combine(queryFlow, refreshTrigger.onStart { emit(Unit) }) { query,_ -> query }
        .debounce(DurationConstants.SEARCH_DEBOUNCE_DELAY)
        .flatMapLatest { query ->
            val result = getMedicines(query)
            updateRefreshing(false)
            result
        }.cachedIn(viewModelScope)

    fun onAction(action: MedicinesSearchUIAction){
        when(action){
            is MedicinesSearchUIAction.AddMedicineToPrescription -> TODO()
            MedicinesSearchUIAction.Clear -> TODO()
            MedicinesSearchUIAction.CloseBottomSheet -> TODO()
            MedicinesSearchUIAction.Finish -> TODO()
            MedicinesSearchUIAction.OpenBottomSheet -> TODO()
            is MedicinesSearchUIAction.RemoveMedicineFromPrescription -> TODO()
            MedicinesSearchUIAction.Refresh ->{
                viewModelScope.launch {
                    updateRefreshing(true)
                    refreshTrigger.emit(Unit)
                }
            }
            MedicinesSearchUIAction.ToggleTopBarState ->{
                _uiState.value = _uiState.value.copy(topBarState = _uiState.value.topBarState.toggle())
            }
            is MedicinesSearchUIAction.UpdateQuery ->{
                _uiState.value = _uiState.value.copy(query= action.newQuery)
            }
            is MedicinesSearchUIAction.UpdateScreenState ->{
                _uiState.value = _uiState.value.copy(screenState = action.newState)
            }
        }
    }
    private fun showToast(message: UiText) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateRefreshing(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun TopBarState.toggle()=
        when(this){
            TopBarState.DEFAULT -> TopBarState.SEARCH
            TopBarState.SEARCH -> TopBarState.DEFAULT
        }
}