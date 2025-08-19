package com.example.medical_prescription_details.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.doctor.prescription.GetPrescriptionDetailsUseCase
import com.example.prescription_details.navigation.PrescriptionDetailsRoute
import com.example.model.enums.ScreenState
import com.example.model.prescription.PrescriptionDetails
import com.example.prescription_details.main.PrescriptionDetailsBusinessUiActions
import com.example.prescription_details.main.PrescriptionDetailsNavigationUiActions
import com.example.prescription_details.main.PrescriptionDetailsUiActions
import com.example.prescription_details.main.PrescriptionDetailsUiState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PrescriptionDetailsViewModel(
    private val getPrescriptionDetailsUseCase: GetPrescriptionDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PrescriptionDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val routeArgs = savedStateHandle.toRoute<PrescriptionDetailsRoute>()
        _uiState.update {
            it.copy(
                prescriptionId = routeArgs.prescriptionId,
            )
        }
        getPrescriptionDetails()
    }


    fun getUiActions(
        navActions: PrescriptionDetailsNavigationUiActions,
    ): PrescriptionDetailsUiActions = PrescriptionDetailsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): PrescriptionDetailsBusinessUiActions =
        object : PrescriptionDetailsBusinessUiActions {
            override fun onGetPrescriptionDetails() {
                getPrescriptionDetails()
            }

            override fun onUpdateSelectedMedicineIndex(index: Int?) {
                updateSelectedMedicineIndex(index)
            }

            override fun onRefresh() {
                refreshPrescriptionDetails()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }
        }

    private fun updatePrescriptionDetails(prescription: PrescriptionDetails?) {
        _uiState.update {
            it.copy(
                prescription = prescription,
            )
        }
    }

    private fun updateSelectedMedicineIndex(index: Int?) {
        _uiState.update {
            it.copy(
                selectedMedicineIndex = index,
            )
        }
    }


    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update {
            it.copy(screenState = screenState)
        }
    }

    private fun getPrescriptionDetails() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Getting PrescriptionDetails", "PrescriptionDetailsViewModel")
            getPrescriptionDetailsUseCase(uiState.value.prescriptionId?:-1)
                .onSuccess { data ->
                    Log.v(
                        "PrescriptionDetails fetched Successfully",
                        "PrescriptionDetailsViewModel"
                    )
                    updatePrescriptionDetails(data)
                    updateScreenState(ScreenState.SUCCESS)
                }.onError { error ->
                    Log.v("Failed to fetch PrescriptionDetails", "PrescriptionDetailsViewModel")
                    updatePrescriptionDetails(null)
                    updateScreenState(ScreenState.ERROR)
                }
        }
    }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun refreshPrescriptionDetails() {
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Getting PrescriptionDetails", "PrescriptionDetailsViewModel")
            getPrescriptionDetailsUseCase(uiState.value.prescriptionId?:-1)
                .onSuccess { data ->
                    Log.v(
                        "PrescriptionDetails fetched Successfully",
                        "PrescriptionDetailsViewModel"
                    )
                    updateIsRefreshing(false)
                    updatePrescriptionDetails(data)
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch PrescriptionDetails", "PrescriptionDetailsViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }


}