package com.example.prescription_details.main

import com.example.model.enums.ScreenState
import com.example.model.prescription.PrescriptionDetails
import com.example.util.UiText

data class PrescriptionDetailsUiState(
    val prescriptionId: Int? = null,
    val prescription: PrescriptionDetails? = null,

    val selectedMedicineIndex: Int? = null,

    val screenState: ScreenState = ScreenState.IDLE,

    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,
)