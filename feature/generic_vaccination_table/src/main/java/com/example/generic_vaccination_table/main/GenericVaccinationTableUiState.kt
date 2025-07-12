package com.example.generic_vaccination_table.main

import com.example.model.enums.ScreenState
import com.example.model.vaccine.GenericVaccinationTable
import com.example.model.vaccine.UpdateVaccinationTableRequest
import com.example.util.UiText

data class GenericVaccinationTableUiState(
    val vaccinationTable: GenericVaccinationTable?=null,

    val showSaveButton: Boolean=false,
    val updateVaccinationTableRequest: UpdateVaccinationTableRequest?=null,

    val screenState: ScreenState = ScreenState.IDLE,

    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,
)
