package com.example.pharmacies.presentaion

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.pharmacy.GetPharmaciesByMedicinesIdUseCase
import com.example.model.enums.ScreenState
import com.example.model.pharmacy.PharmacyData
import com.example.pharmacies.navigation.PharmaciesRoute
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.ui_components.R

class PharmaciesViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPharmaciesByMedicinesId: GetPharmaciesByMedicinesIdUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(
        PharmaciesUIState(
            medicineId = savedStateHandle.toRoute<PharmaciesRoute>().medicineId,
            medicineName = savedStateHandle.toRoute<PharmaciesRoute>().medicineName
        )
    )

    val uiState: StateFlow<PharmaciesUIState> = _uiState.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _uiState.value
    )

    fun onAction(action: PharmaciesUIAction){
        when(action){
            PharmaciesUIAction.Refresh -> refreshData()
            PharmaciesUIAction.ClearToast -> clearToast()
        }
    }
    private fun clearToast(){
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }
    private fun loadData()= viewModelScope.launch{
        updateScreenState(ScreenState.LOADING)
        getPharmaciesByMedicinesId(medicineId = uiState.value.medicineId)
            .onSuccess{ pharmacies: List<PharmacyData>->
                updatePharmacies(pharmacies)
                updateScreenState(ScreenState.SUCCESS)
            }.onError {
                updateScreenState(ScreenState.ERROR)
            }
    }
    private fun updatePharmacies(pharmacies: List<PharmacyData>){
        _uiState.value = _uiState.value.copy(data = pharmacies)
    }
    private fun showToast(message: UiText) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(
            state = newState
        )
    }
    private fun updateRefreshState(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun refreshData() = viewModelScope.launch{
        updateRefreshState(true)
        getPharmaciesByMedicinesId(medicineId = _uiState.value.medicineId)
            .onSuccess { pharmacies: List<PharmacyData> ->
                updateRefreshState(false)
                showToast(UiText.StringResource(R.string.data_updated_successfully))
                updatePharmacies(pharmacies)
                if (uiState.value.state == ScreenState.ERROR) {
                    updateScreenState(ScreenState.SUCCESS)
                }
            }.onError {
                updateRefreshState(false)
                showToast(UiText.StringResource(R.string.something_went_wrong))
            }
    }
}