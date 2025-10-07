package com.example.generic_vaccination_table.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.vaccine.GetGenericVaccinationTableUseCase
import com.example.domain.use_cases.vaccine.GetVaccinesWithNoVisitNumberUserCase
import com.example.domain.use_cases.vaccine.UpdateVaccineVisitNumberUseCase
import com.example.domain.use_cases.vaccine.UpdateVaccinesVisitNumberUseCase
import com.example.ext.toOrdinalString
import com.example.generic_vaccination_table.navigation.GenericVaccinationTableRoute
import com.example.model.enums.ScreenState
import com.example.model.vaccine.GenericVaccinationTableData
import com.example.model.vaccine.VaccinationTableVisit
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.model.vaccine.VaccineMainInfo
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GenericVaccinationTableViewModel(
    private val getGenericVaccinationTableUseCase: GetGenericVaccinationTableUseCase,
    private val updateVaccineVisitNumberUseCase: UpdateVaccineVisitNumberUseCase,
    private val updateVaccinesVisitNumberUseCase: UpdateVaccinesVisitNumberUseCase,
    private val getVaccinesWithNoVisitNumberUserCase: GetVaccinesWithNoVisitNumberUserCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GenericVaccinationTableUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val genericVaccinationTableRoute = savedStateHandle.toRoute<GenericVaccinationTableRoute>()
        _uiState.update { it.copy(genericVaccinationTableAccessType = genericVaccinationTableRoute.genericVaccinationTableAccessType) }
        getGenericVaccinationTable()
    }

    private var vaccineFetchJob: Job? = null


    fun getUiActions(
        navActions: GenericVaccinationTableNavigationUiActions,
    ): GenericVaccinationTableUiActions = GenericVaccinationTableUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): GenericVaccinationTableBusinessUiActions =
        object : GenericVaccinationTableBusinessUiActions {
            override fun onAddVaccineToVisitClick(visitNumber: Int) {
                updateVisitNumberToUse(visitNumber)
                updateVaccinesSelectionDialogVisibility(true)
                getVaccineWithNoVisitNumber()
            }

            override fun onHideVaccinesDialog() {
                updateVaccinesSelectionDialogVisibility(false)
                updateVaccinesWithNotVisitNumber(null)
                updateGetVaccinesWithNoVisitNumberScreenState(ScreenState.IDLE)
            }

            override fun onCancelVaccinesFetch() {
                cancelVaccinesFetch()
            }

            override fun onShowVaccinesDialog() {
                updateVaccinesSelectionDialogVisibility(true)
            }

            override fun onRemoveVaccineFromVisit(
                visitNumber: Int,
                vaccineIndex: Int
            ) {
                removeVaccineFromVisit()
                onHideConfirmationDialogVisibility()
            }

            override fun onSetVisitNumberAndVaccineIndex(
                visitNumber: Int,
                vaccineIndex: Int
            ) {
                updateVisitNumberToUse(visitNumber)
                updateVaccineIndexToDelete(vaccineIndex)
                onShowDeleteConfirmationDialog()
            }

            override fun onAddNewVisit() {
                addNewVisit()
            }

            override fun onUpdateConfirmationDialogVisibility(isVisible: Boolean) {
                updateConfirmationDialogVisibility(isVisible)
            }

            override fun onCleanUpSelectedVaccinesIndices() {
                cleanUpSelectedVaccines()
            }

            override fun onVaccineOptionSelected(index: Int, isSelected: Boolean) {
                updateVaccineOptionSelectionState(index, isSelected)
            }

            override fun onShowDiscardChangesConfirmationDialog() {
                showDiscardChangesConfirmationDialog()
            }

            override fun hideConfirmationDialog() {
                onHideConfirmationDialogVisibility()
            }

            override fun onShowDeleteConfirmationDialog() {
                showDeleteConfirmationDialog()
            }

            override fun onAddVaccinesToVisit(indexes: List<Int>) {
                addVaccineToVisit(indexes)
                cleanUpSelectedVaccines()
                updateGetVaccinesWithNoVisitNumberScreenState(ScreenState.IDLE)
                updateVaccinesWithNotVisitNumber(null)
                updateVaccinesSelectionDialogVisibility(false)
            }

            override fun onRefresh() {
                refreshGenericVaccinationTable()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

            override fun onRetryFetchVaccinesWithNoVisitNumber() {
                getVaccineWithNoVisitNumber()
            }

        }

    private fun cleanUpSelectedVaccines() {
        _uiState.update { it.copy(selectedVaccinesIndices = emptyList()) }
    }

    private fun updateVaccineOptionSelectionState(index: Int, value: Boolean) {
        val newList = uiState.value.selectedVaccinesIndices.toMutableList()
        if (!value)
            newList.add(index)
        else
            newList.remove(index)
        _uiState.update { it.copy(selectedVaccinesIndices = newList) }
    }

    fun updateConfirmationDialogVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(isDeleteConfirmationDialogVisible = isVisible) }
    }

    private fun updateVisitNumberToUse(visitNumber: Int?) {
        _uiState.update { it.copy(visitNumberToUse = visitNumber) }
    }

    private fun updateVaccineIndexToDelete(vaccineIndex: Int?) {
        _uiState.update { it ->
            it.copy(
                vaccineIdToDelete =
                    uiState.value.vaccinationTable?.visits?.find { visit ->
                        uiState.value.visitNumberToUse == visit.visitNumber
                    }
                        ?.vaccines[vaccineIndex ?: -1]?.id
            )
        }
    }

    private fun addNewVisit() {
        val newVisitNumber =
            uiState.value.vaccinationTable?.visits?.maxOfOrNull { it.visitNumber }?.plus(1) ?: 1

        val newVisits = uiState.value.vaccinationTable?.visits?.toMutableList() ?: mutableListOf()
        newVisits.add(
            VaccinationTableVisit(visitNumber = newVisitNumber, vaccines = emptyList())
        )

        updateGenericVaccinationTable(GenericVaccinationTableData(visits = newVisits))
    }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun updateGetTableScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(screenState = screenState) }
    }

    private fun updateGenericVaccinationTable(genericVaccinationTableData: GenericVaccinationTableData?) {
        _uiState.update { it.copy(vaccinationTable = genericVaccinationTableData) }
    }

    private fun onHideConfirmationDialogVisibility() {
        _uiState.update { it.copy(isDeleteConfirmationDialogVisible = false) }
    }

    private fun showDeleteConfirmationDialog() {
        _uiState.update {
            it.copy(
                isDeleteConfirmationDialogVisible = true,
                dialogConfirmText = UiText.StringResource(R.string.remove),
                dialogCancelText = UiText.StringResource(R.string.cancel),
                dialogTitleText = UiText.StringResource(R.string.remove_vaccine),
                dialogDescriptionText = UiText.StringResource(
                    R.string.are_you_sure_you_want_to_remove_vaccinename_from_visit_visitNumber,
                    args = listOf(
                        uiState.value.vaccinationTable?.visits?.find {visit->
                            uiState.value.visitNumberToUse == visit.visitNumber
                        }
                            ?.vaccines?.find { it.id == uiState.value.vaccineIdToDelete }?.name
                            ?: "",
                        (uiState.value.visitNumberToUse ?: -1).toOrdinalString()
                    )
                ),
            )
        }
    }

    private fun showDiscardChangesConfirmationDialog() {
        _uiState.update {
            it.copy(
                isDeleteConfirmationDialogVisible = false,
                dialogConfirmText = UiText.StringResource(R.string.continue_),
                dialogCancelText = UiText.StringResource(R.string.discard),
                dialogTitleText = UiText.StringResource(R.string.unsaved_changes),
                dialogDescriptionText = UiText.StringResource(R.string.discard_changes_dialog_description),
            )
        }
    }


    private fun getGenericVaccinationTable() {
        viewModelScope.launch {
            updateGetTableScreenState(ScreenState.LOADING)
            Log.v("Fetching Generic Vaccination Table", "GenericVaccinationTableViewModel")
            getGenericVaccinationTableUseCase()
                .onSuccess { data ->
                    Log.v(
                        "Generic Vaccination Table fetched Successfully",
                        "GenericVaccinationTableViewModel"
                    )
                    updateGetTableScreenState(ScreenState.SUCCESS)
                    updateGenericVaccinationTable(data)
                }.onError { error ->
                    Log.v(
                        "Failed to fetch Generic Vaccination Table",
                        "GenericVaccinationTableViewModel"
                    )
                    updateGetTableScreenState(ScreenState.ERROR)
                    updateGenericVaccinationTable(null)
                }
        }
    }

    private fun updateVaccinesSelectionDialogVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(showVaccinesDialog = isVisible) }
    }

    private fun updateGetVaccinesWithNoVisitNumberScreenState(screenState: ScreenState) {
        _uiState.update {
            it.copy(
                getVaccinesWithNoVisitNumberScreenState = screenState
            )
        }
    }

    private fun updateVaccinesWithNotVisitNumber(vaccines: List<VaccineMainInfo>?) {
        _uiState.update {
            it.copy(
                vaccines = vaccines
            )
        }
    }


    private fun getVaccineWithNoVisitNumber() {
        vaccineFetchJob?.cancel()

        vaccineFetchJob = viewModelScope.launch {
            updateGetVaccinesWithNoVisitNumberScreenState(ScreenState.LOADING)
            Log.v("Fetching Generic Vaccination Table", "GenericVaccinationTableViewModel")
            getVaccinesWithNoVisitNumberUserCase()
                .onSuccess { data ->
                    Log.v(
                        "Generic Vaccination Table fetched Successfully",
                        "GenericVaccinationTableViewModel"
                    )
                    updateGetVaccinesWithNoVisitNumberScreenState(ScreenState.SUCCESS)
                    updateVaccinesWithNotVisitNumber(data)
                    vaccineFetchJob = null
                }.onError { error ->
                    Log.v(
                        "Failed to fetch Generic Vaccination Table",
                        "VaccinesWithNotVisitNumberViewModel"
                    )
                    updateGetVaccinesWithNoVisitNumberScreenState(ScreenState.ERROR)
                    updateVaccinesWithNotVisitNumber(null)
                    vaccineFetchJob = null
                }
        }
    }

    private fun cancelVaccinesFetch() {
        vaccineFetchJob?.cancel()
        updateGetVaccinesWithNoVisitNumberScreenState(ScreenState.IDLE)
        vaccineFetchJob = null
    }

    private fun pushNewVaccineToDelete(vaccineId:Int){
        val vaccinesIdsToDelete=uiState.value.vaccinesIdsToDelete.toMutableList()
        vaccinesIdsToDelete.add(vaccineId)
        _uiState.update { it.copy(vaccinesIdsToDelete = vaccinesIdsToDelete) }
    }

    private fun pollVaccineToDelete(vaccineId:Int){
        val vaccinesIdsToDelete=uiState.value.vaccinesIdsToDelete.toMutableList()
        vaccinesIdsToDelete.remove(vaccineId)
        _uiState.update { it.copy(vaccinesIdsToDelete = vaccinesIdsToDelete) }
    }

    private fun removeVaccineFromVisit() {
        val visitNumberToUse = uiState.value.visitNumberToUse
        Log.v("VisitNumber", visitNumberToUse.toString())
        if (visitNumberToUse == null) return
        val vaccineIdToDelete = uiState.value.vaccineIdToDelete
        Log.v("VaccineId", vaccineIdToDelete.toString())
        if (vaccineIdToDelete == null) return

        pushNewVaccineToDelete(vaccineIdToDelete)

        viewModelScope.launch {
            Log.v("Removing Vaccine to Visit", "GenericVaccinationTableViewModel")
            updateVaccineVisitNumberUseCase(
                VaccineIdToVisitNumber(
                    visitNumber = null,
                    vaccineId = vaccineIdToDelete
                )
            )
                .onSuccess { data ->
                    Log.v(
                        "Vaccine Removed Successfully",
                        "GenericVaccinationTableViewModel"
                    )
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateGetTableScreenState(ScreenState.SUCCESS)
                    }
                    updateGenericVaccinationTable(data)
                    updateToastMessage(null)
                    pollVaccineToDelete(vaccineIdToDelete)
                }.onError { error ->
                    Log.v(
                        "Failed to remove vaccine",
                        "GenericVaccinationTableViewModel"
                    )
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                    pollVaccineToDelete(vaccineIdToDelete)
                }
        }
    }

    private fun setLoadingVisitNumber(visitNumber: Int?) {
        _uiState.update { it.copy(loadingVisitNumber = visitNumber) }
    }

    private fun addVaccineToVisit(indexes: List<Int>?) {
        val visitNumberToUse = uiState.value.visitNumberToUse
        if (visitNumberToUse == null) return

        val vaccines = uiState.value.vaccines
        if (vaccines == null) return

        setLoadingVisitNumber(visitNumberToUse)
        val vaccinesIdsToAdd = indexes?.map { vaccines[it].id }

        viewModelScope.launch {
            Log.v("Adding Vaccine to Visit", "GenericVaccinationTableViewModel")
            setLoadingVisitNumber(visitNumberToUse)
            updateVaccinesVisitNumberUseCase(
                VaccinesIdsToVisitNumber(
                    visitNumber = visitNumberToUse, vaccinesIds = vaccinesIdsToAdd
                )
            )
                .onSuccess { data ->
                    Log.v(
                        "Vaccine Added Successfully",
                        "GenericVaccinationTableViewModel"
                    )
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateGetTableScreenState(ScreenState.SUCCESS)
                    }
                    updateGenericVaccinationTable(data)
                    updateToastMessage(null)
                }.onError { error ->
                    Log.v(
                        "Failed to add vaccine",
                        "GenericVaccinationTableViewModel"
                    )
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
            setLoadingVisitNumber(null)
        }
    }

    private fun refreshGenericVaccinationTable() {
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Generic Vaccination Table", "GenericVaccinationTableViewModel")
            getGenericVaccinationTableUseCase()
                .onSuccess { data ->
                    Log.v(
                        "Generic Vaccination Table fetched Successfully",
                        "GenericVaccinationTableViewModel"
                    )
                    updateIsRefreshing(false)
                    updateGenericVaccinationTable(data)
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