package com.example.medicines_search.presentation.preview

import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState
import com.example.model.medicine.MedicineData
import com.example.util.UiText

data class MedicinesSearchUIState(
    val doctorId: Int,
    val appointmentId: Int,
    val isRefreshing: Boolean = false,
    val screenState: ScreenState = ScreenState.IDLE,
    val clearButtonState: BottomBarState = BottomBarState.DISABLED,
    val finishButtonState: BottomBarState = BottomBarState.IDLE,
    val query: String = "",
    val topBarState: TopBarState = TopBarState.DEFAULT,
    val prescriptionMedicines: List<MedicineData> = emptyList(),
    val toastMessage: UiText? = null,
    val isBottomSheetOpened: Boolean = false,
    val selectedMedicines: List<MedicineData> = emptyList()
)


