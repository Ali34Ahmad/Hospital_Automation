package com.example.medicines_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.example.domain.use_cases.doctor.prescription.AddPrescriptionUseCase
import com.example.domain.use_cases.medicine.GetMedicinesFlowUseCase
import com.example.medicines_search.navigation.MedicinesSearchRoute
import com.example.model.prescription.PrescriptionMedicineData
import com.example.util.UiText
import com.example.utility.constants.DurationConstants
import com.example.utility.network.onError
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
import com.example.ui_components.R
import com.example.util.UiText.*
import com.example.utility.network.onSuccess

class MedicineSearchViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getMedicines: GetMedicinesFlowUseCase,
    private val addPrescription: AddPrescriptionUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(
        MedicinesSearchUIState(
            appointmentId= savedStateHandle.toRoute<MedicinesSearchRoute>().appointmentId,
            childId = savedStateHandle.toRoute<MedicinesSearchRoute>().childId,
            patientId = savedStateHandle.toRoute<MedicinesSearchRoute>().patientId
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
            is MedicinesSearchUIAction.AddMedicineToPrescription -> {
                if(_uiState.value.selectedMedicines.find { it.first == action.medicine } != null){
                    return
                }
                val newList = _uiState.value.selectedMedicines + Pair(action.medicine,"")
                _uiState.value = _uiState.value.copy(selectedMedicines = newList)
            }
            MedicinesSearchUIAction.Clear -> {
                _uiState.value = _uiState.value.copy(selectedMedicines = emptyList())
            }
            MedicinesSearchUIAction.CloseBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetOpened = false)
            }
            MedicinesSearchUIAction.Finish ->viewModelScope.launch{
                // if no data in the prescription just navigate to the next screen
                if(_uiState.value.selectedMedicines.isEmpty()){
                    _uiState.value = _uiState.value.copy(isDataSentSuccessfully = true)
                    return@launch
                }
                showLoadingDialog()
                addPrescription(
                    childId = uiState.value.childId,
                    patientId = uiState.value.patientId,
                    note = "",
                    medicines = _uiState.value.selectedMedicines.map { (medicine,note)->
                        val newNote = note
                        PrescriptionMedicineData(
                            medicine.medicineId,newNote
                        )
                    }
                ).onError{
                    showToast(StringResource(R.string.something_went_wrong))
                }.onSuccess {
                    _uiState.value = _uiState.value.copy(isDataSentSuccessfully = true)
                }
                hideLoadingDialog()
            }
            MedicinesSearchUIAction.OpenBottomSheet -> {
                _uiState.value = _uiState.value.copy(isBottomSheetOpened = true)
            }
            is MedicinesSearchUIAction.RemoveMedicineFromPrescription ->{
                val newList = _uiState.value.selectedMedicines.filterNot { (med,_)-> med==action.medicine }
                _uiState.value = _uiState.value.copy(selectedMedicines = newList)
            }
            MedicinesSearchUIAction.Refresh ->{
                viewModelScope.launch {
                    updateRefreshing(true)
                    refreshTrigger.emit(Unit)
                }
            }
            is MedicinesSearchUIAction.UpdateQuery ->{
                _uiState.value = _uiState.value.copy(query= action.newQuery)
            }
            is MedicinesSearchUIAction.UpdateScreenState ->{
                _uiState.value = _uiState.value.copy(screenState = action.newState)
            }
            is MedicinesSearchUIAction.AddNote ->{
                val newList = _uiState.value.selectedMedicines.map {
                    if(it.first.medicineId == action.medicineId){
                        it.first to action.note
                    }else{
                        it
                    }
                }
                _uiState.value = _uiState.value.copy(selectedMedicines = newList, isNoteDialogOpened = false)
            }
            is MedicinesSearchUIAction.OpenNoteDialog -> {
                val dialogContent = _uiState.value.selectedMedicines.first { it.first.medicineId == action.medicineId }
                _uiState.value = _uiState.value.copy(
                    isNoteDialogOpened = true,
                    dialogMedicine = dialogContent.first,
                    dialogNote = dialogContent.second,
                    )
            }
            MedicinesSearchUIAction.CloseNoteDialog ->{
                _uiState.value = _uiState.value.copy(isNoteDialogOpened = false)
            }
            is MedicinesSearchUIAction.UpdateNote ->{
                _uiState.value = _uiState.value.copy(dialogNote = action.newNote)
            }
            MedicinesSearchUIAction.ClearToast -> {
                _uiState.value = _uiState.value.copy(toastMessage = null)
            }
        }
    }

    private fun showToast(message: UiText) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateRefreshing(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun hideLoadingDialog(){
        _uiState.value = _uiState.value.copy(isLoadingDialogShown = false)
    }
    private fun showLoadingDialog(){
        _uiState.value = _uiState.value.copy(isLoadingDialogShown = true)
    }
}