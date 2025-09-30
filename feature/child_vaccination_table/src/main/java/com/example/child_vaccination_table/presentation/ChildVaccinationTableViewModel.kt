package com.example.child_vaccination_table.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.child_vaccination_table.navigation.ChildVaccinationTableRoute
import com.example.domain.use_cases.vaccine.GetChildVaccinationTableUseCase
import com.example.model.enums.ScreenState
import com.example.model.vaccine.ChildVaccinationTableData
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChildVaccinationTableViewModel(
    private val getChildVaccinationTableUseCase: GetChildVaccinationTableUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChildVaccinationTableUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val genericVaccinationTableRoute = savedStateHandle.toRoute<ChildVaccinationTableRoute>()
        _uiState.update { it.copy(childId = genericVaccinationTableRoute.childId) }
        getChildVaccinationTable()
    }

    fun getUiActions(
        navActions: ChildVaccinationTableNavigationUiActions,
    ): ChildVaccinationTableUiActions = ChildVaccinationTableUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): ChildVaccinationTableBusinessUiActions =
        object : ChildVaccinationTableBusinessUiActions {
            override fun onRefresh() {
                refreshGenericVaccinationTable()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

        }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun updateGetTableScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(screenState = screenState) }
    }

    private fun updateChildVaccinationTable(childVaccinationTable: ChildVaccinationTableData?) {
        _uiState.update { it.copy(vaccinationTable = childVaccinationTable) }
    }

    private fun getChildVaccinationTable() {
        val childId = uiState.value.childId
        if (childId == null) return
        viewModelScope.launch {
            updateGetTableScreenState(ScreenState.LOADING)
            Log.v("Fetching Generic Vaccination Table", "GenericVaccinationTableViewModel")
            getChildVaccinationTableUseCase(childId)
                .onSuccess { data ->
                    Log.v(
                        "Generic Vaccination Table fetched Successfully",
                        "GenericVaccinationTableViewModel"
                    )
                    updateGetTableScreenState(ScreenState.SUCCESS)
                    updateChildVaccinationTable(data)
                }.onError { error ->
                    Log.v(
                        "Failed to fetch Generic Vaccination Table",
                        "GenericVaccinationTableViewModel"
                    )
                    updateGetTableScreenState(ScreenState.ERROR)
                    updateChildVaccinationTable(null)
                }
        }
    }

    private fun refreshGenericVaccinationTable() {
        val childId = uiState.value.childId
        if (childId == null) return
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Generic Vaccination Table", "GenericVaccinationTableViewModel")
            getChildVaccinationTableUseCase(childId)
                .onSuccess { data ->
                    Log.v(
                        "Generic Vaccination Table fetched Successfully",
                        "GenericVaccinationTableViewModel"
                    )
                    updateIsRefreshing(false)
                    updateChildVaccinationTable(data)
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateGetTableScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v(
                        "Failed to fetch Generic Vaccination Table",
                        "GenericVaccinationTableViewModel"
                    )
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }
}