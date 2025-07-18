package com.example.pharmacies.presentaion

import com.example.model.enums.ScreenState
import com.example.model.pharmacy.PharmacyData
import com.example.util.UiText

data class PharmaciesUIState(
    val medicineId: Int,
    val medicineName: String,
    val data: List<PharmacyData> = emptyList(),
    val state: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
    val toastMessage : UiText? = null,
)
