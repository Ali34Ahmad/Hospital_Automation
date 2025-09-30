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
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccinationTableVisit
import com.example.model.vaccine.VaccineIdToVisitNumber
import com.example.model.vaccine.VaccineMainInfo
import com.example.model.vaccine.VaccinesIdsToVisitNumber
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
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
                updateVaccinesSelectionDialogVisibility(true)
                updateVaccinesWithNotVisitNumber(null)
            }

            override fun onShowVaccinesDialog() {
                updateVaccinesSelectionDialogVisibility(false)
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

            override fun onUpdateVaccinesSelectionDialogVisibility(isVisible: Boolean) {
                TODO()
            }

            override fun onUpdateConfirmationDialogVisibility(isVisible: Boolean) {
                updateConfirmationDialogVisibility(isVisible)
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

            override fun onGetGenericVaccinationTable() {
                getGenericVaccinationTable()
            }

            override fun onAddVaccinesToVisit(indexes: List<Int>) {
                addVaccineToVisit(indexes)
                updateVaccinesSelectionDialogVisibility(false)
            }

            override fun onRefresh() {
                refreshGenericVaccinationTable()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

        }

    fun updateConfirmationDialogVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(isDeleteConfirmationDialogVisible = isVisible) }
    }

    private fun updateVisitNumberToUse(visitNumber: Int?) {
        _uiState.update { it.copy(visitNumberToUse = visitNumber) }
    }

    private fun updateVaccineIndexToDelete(vaccineIndex: Int?) {
        _uiState.update {
            it.copy(
                vaccineIdToDelete =
                    uiState.value.vaccinationTable?.visits?.find {
                        uiState.value.visitNumberToUse == it.visitNumber
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

        updateGenericVaccinationTable(GenericVaccinationTable(visits = newVisits))
    }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun updateGetTableScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(screenState = screenState) }
    }

    private fun updateGenericVaccinationTable(genericVaccinationTable: GenericVaccinationTable?) {
        _uiState.update { it.copy(vaccinationTable = genericVaccinationTable) }
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
                        uiState.value.vaccinationTable?.visits?.find {
                            uiState.value.visitNumberToUse == it.visitNumber
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
        viewModelScope.launch {
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
                }.onError { error ->
                    Log.v(
                        "Failed to fetch Generic Vaccination Table",
                        "VaccinesWithNotVisitNumberViewModel"
                    )
                    updateGetVaccinesWithNoVisitNumberScreenState(ScreenState.ERROR)
                    updateVaccinesWithNotVisitNumber(null)
                }
        }
    }

    private fun removeVaccineFromVisit() {
        val visitNumberToUse = uiState.value.visitNumberToUse
        Log.v("VisitNumber", visitNumberToUse.toString())
        if (visitNumberToUse == null) return
        val vaccineIdToDelete = uiState.value.vaccineIdToDelete
        Log.v("VaccineId", vaccineIdToDelete.toString())
        if (vaccineIdToDelete == null) return

        Log.v("VaccineId", vaccineIdToDelete.toString())

        viewModelScope.launch {
            Log.v("Removing Vaccine to Visit", "GenericVaccinationTableViewModel")
//            addToVaccinesToDelete(vaccineIdToDelete)
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
                }.onError { error ->
                    Log.v(
                        "Failed to remove vaccine",
                        "GenericVaccinationTableViewModel"
                    )
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
//            removeFromVaccinesToDelete(vaccineId = vaccineIdToDelete)
        }
    }

//    private fun addToVaccinesToDelete(vaccineId: Int) {
//        val vaccinesToDelete = uiState.value.vaccinesIdsToDelete.toMutableList()
//        vaccinesToDelete.add(vaccineId)
//        _uiState.update { it.copy(vaccinesIdsToDelete = vaccinesToDelete) }
//    }

//    private fun removeFromVaccinesToDelete(vaccineId: Int) {
//        val vaccinesToDelete = uiState.value.vaccinesIdsToDelete.toMutableList()
//        vaccinesToDelete.remove(vaccineId)
//        _uiState.update { it.copy(vaccinesIdsToDelete = vaccinesToDelete) }
//    }

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