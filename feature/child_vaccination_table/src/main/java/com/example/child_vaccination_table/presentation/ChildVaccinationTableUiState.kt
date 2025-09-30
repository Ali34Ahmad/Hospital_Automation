package com.example.child_vaccination_table.presentation

import com.example.model.enums.ScreenState
import com.example.model.vaccine.ChildVaccinationTableData
import com.example.util.UiText

data class ChildVaccinationTableUiState(
    val childId:Int?=null,
    val vaccinationTable: ChildVaccinationTableData? = null,

    val screenState: ScreenState = ScreenState.IDLE,

    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,
)
