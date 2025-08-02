package com.example.medicines_search.presentation

import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineData
import com.example.util.UiText

data class MedicinesSearchUIState(
    val appointmentId: Int,
    val childId: Int?,
    val patientId: Int?,
    val isRefreshing: Boolean = false,
    val screenState: ScreenState = ScreenState.IDLE,
    val query: String = "",
    val prescriptionMedicines: List<MedicineData> = emptyList(),
    val toastMessage: UiText? = null,
    val isBottomSheetOpened: Boolean = false,
    val selectedMedicines: List<Pair<MedicineData, String>> = emptyList(),
    val isNoteDialogOpened: Boolean = false,
    val isDataSentSuccessfully : Boolean = false,
    val dialogMedicine: MedicineData? = null,
    val dialogNote: String = "",
    val isLoadingDialogShown: Boolean = false
){
    val bottomSheetMedicines : List<MedicineData>
        get() = selectedMedicines.map { it.first }

}


