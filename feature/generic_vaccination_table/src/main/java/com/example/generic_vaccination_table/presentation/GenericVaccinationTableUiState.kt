package com.example.generic_vaccination_table.presentation

import com.example.generic_vaccination_table.navigation.GenericVaccinationTableAccessType
import com.example.model.enums.ScreenState
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.VaccineMainInfo
import com.example.util.UiText

data class GenericVaccinationTableUiState(
    val vaccinationTable: GenericVaccinationTable? = null,
    val genericVaccinationTableAccessType: GenericVaccinationTableAccessType? = null,

    val loadingVisitNumber: Int? = null,

    val visitNumberToUse: Int? = null,
    val vaccineIdToDelete: Int? = null,

//    val vaccinesIdsToDelete: List<Int> = emptyList(),

    val screenState: ScreenState = ScreenState.IDLE,

    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,

    val isDeleteConfirmationDialogVisible: Boolean = false,
    val dialogConfirmText: UiText? = null,
    val dialogCancelText: UiText? = null,
    val dialogTitleText: UiText? = null,
    val dialogDescriptionText: UiText? = null,

    val vaccines: List<VaccineMainInfo>? = null,
    val showVaccinesDialog: Boolean=false,
    val getVaccinesWithNoVisitNumberScreenState: ScreenState=ScreenState.IDLE,
)
