package com.example.medicine_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.medicine.GetMedicineByIdUseCase
import com.example.medicine_details.navigation.MedicineDetailsRoute
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineDetailsData
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ui_components.R
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MedicineDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getMedicine: GetMedicineByIdUseCase
): ViewModel() {
    private val route  = savedStateHandle.toRoute<MedicineDetailsRoute>()

    private val _uiState = MutableStateFlow(
        MedicineDetailsUIState(
        medicineId = route.medicineId
    ))
    val uiState: StateFlow<MedicineDetailsUIState> = _uiState.onStart {
        loadData()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        _uiState.value
    )

    fun onAction(action: MedicineDetailsUIActions){
        when(action){
            MedicineDetailsUIActions.Refresh -> {
                refreshData()
            }
            is MedicineDetailsUIActions.SelectNewMedicine->{
                updateMedicineId(newId = action.medicineId)
                hideDialog()
                refreshData()
            }
            MedicineDetailsUIActions.ShowDialog -> showDialog()
            MedicineDetailsUIActions.HideDialog -> hideDialog()
            MedicineDetailsUIActions.ClearToastMessage -> {
                clearToast()
            }
        }
    }
    private fun clearToast(){
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }
    private fun updateMedicineId(newId: Int){
        _uiState.value = _uiState.value.copy(medicineId = newId)
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    private fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun showDialog(){
        _uiState.value = _uiState.value.copy(isDialogShown = true)
    }
    private fun hideDialog(){
        _uiState.value = _uiState.value.copy(isDialogShown = false)
    }
    private fun updateMedicine(newMedicine: MedicineDetailsData){
        _uiState.value = _uiState.value.copy(data = newMedicine)
    }
    private fun updateRefreshing(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun loadData() = viewModelScope.launch{
        updateScreenState(ScreenState.LOADING)
        getMedicine(uiState.value.medicineId)
            .onSuccess{data ->
                updateMedicine(data)
                updateScreenState(ScreenState.SUCCESS)
            }.onError {
                updateScreenState(ScreenState.ERROR)
            }
    }

    private fun refreshData() = viewModelScope.launch{
        val oldId = uiState.value.medicineId
        updateRefreshing(true)
        getMedicine(uiState.value.medicineId)
            .onSuccess{data: MedicineDetailsData->
                val newId = data.medicineId
                updateMedicine(data)
                if(uiState.value.screenState == ScreenState.ERROR){
                    updateScreenState(ScreenState.SUCCESS)
                }
                //if I want to refresh the old data by new one
                if (oldId == newId)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
            }.onError {
                showToast(UiText.StringResource(R.string.something_went_wrong))
            }
        updateRefreshing(false)
    }

}