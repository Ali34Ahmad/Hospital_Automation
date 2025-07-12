package com.example.generic_vaccination_table.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.domain.use_cases.vaccine.GetVaccineByIdUseCase
import com.example.model.enums.ScreenState
import com.example.model.role_config.RoleAppConfig
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccinationTableVisit
import com.example.model.vaccine.VaccineData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class VaccineDetailsViewModel(
    private val getVaccineByIdUseCase: GetVaccineByIdUseCase,
    private val appRoleAppConfig: RoleAppConfig,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GenericVaccinationTableUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getGenericVaccinationTable()
    }

    fun getUiActions(
        navActions: GenericVaccinationTableNavigationUiActions,
    ): GenericVaccinationTableUiActions = GenericVaccinationTableUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): GenericVaccinationTableBusinessUiActions =
        object : GenericVaccinationTableBusinessUiActions {
            override fun onSaveTableChanges() {
                TODO("Not yet implemented")
            }

            override fun onAddVaccineToVisit(visitNumber: Int) {
                TODO("Not yet implemented")
            }

            override fun onRemoveVaccineFromVisit(
                visitNumber: Int,
                vaccineIndex: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onAddNewVisit() {
                TODO("Not yet implemented")
            }

            override fun onUpdateVaccinesSelectionDialogVisibility(isVisible: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onUpdateConfirmationDialogVisibility(isVisible: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onGetGenericVaccinationTable() {
                getGenericVaccinationTable()
            }

            override fun onRefresh() {
                TODO("Not yet implemented")
            }

            override fun clearToastMessage() {
                TODO("Not yet implemented")
            }

        }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(screenState = screenState) }
    }

    private fun updateGenericVaccinationTable(genericVaccinationTable: GenericVaccinationTable?) {
        _uiState.update { it.copy(vaccinationTable = genericVaccinationTable) }
    }

    private fun getGenericVaccinationTable(){

    }
}