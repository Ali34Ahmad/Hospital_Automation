package com.example.medicine_details.presentation

import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineDetailsData
import com.example.util.UiText

data class MedicineDetailsUIState(
    val medicineId: Int,
    val data: MedicineDetailsData? = null,
    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,
    val isDialogShown: Boolean = false,
    val screenState: ScreenState = ScreenState.IDLE
)
