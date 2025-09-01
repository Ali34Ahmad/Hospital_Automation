package com.example.pharmacy_medicines.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.example.domain.use_cases.medicine.GetMedicinesByPharmacyIdUseCase
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.pharmacy_medicines.navigation.PharmacyMedicinesRoute
import com.example.utility.constants.DurationConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PharmacyMedicinesViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val searchForMedicinesByPharmacyId: GetMedicinesByPharmacyIdUseCase,
): ViewModel() {
    private val pharmacyMedicineRoute = savedStateHandle.toRoute<PharmacyMedicinesRoute>()
    private val _uiState = MutableStateFlow(
        PharmacyMedicinesUIState(
            pharmacyId = pharmacyMedicineRoute.pharmacyId,
            title = pharmacyMedicineRoute.name,
            imageUrl = pharmacyMedicineRoute.imageUrl,
        )
    )
    val uiState = _uiState.asStateFlow()

    private val queryFlow = _uiState.map { it.query }.distinctUntilChanged()
    private val refreshTrigger = MutableSharedFlow<Unit>()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val medicinesFlow = combine(
        queryFlow, 
        refreshTrigger.onStart { 
            emit(Unit) 
        }
    ) { query,_ -> query }
        .debounce(DurationConstants.SEARCH_DEBOUNCE_DELAY)
        .flatMapLatest { query ->
            val result = searchForMedicinesByPharmacyId(
                name = query,
                pharmacyId = uiState.value.pharmacyId
            )
            updateRefreshing(false)
            result
        }.cachedIn(viewModelScope)

    private fun updateRefreshing(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    
    fun onAction(action: PharmacyMedicinesUIAction){
        when(action){
            PharmacyMedicinesUIAction.ToggleTopBarState ->
                toggleTopBarState()

            is PharmacyMedicinesUIAction.UpdateQuery ->
                updateQuery(action.newValue)

            is PharmacyMedicinesUIAction.UpdateScreenState ->
                updateScreenState(action.newState)

            PharmacyMedicinesUIAction.Refresh -> refresh()
        }
    }
    private fun toggleTopBarState(){
        val current = uiState.value.topBarState
        val newValue = if (current == TopBarState.DEFAULT) TopBarState.SEARCH else TopBarState.DEFAULT
        _uiState.value = _uiState.value.copy(topBarState = newValue)
    }
    private fun updateQuery(newValue: String){
        _uiState.value = _uiState.value.copy(query = newValue)
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    private fun refresh() =
        viewModelScope.launch {
            updateRefreshing(true)
            refreshTrigger.emit(Unit)
        }
}