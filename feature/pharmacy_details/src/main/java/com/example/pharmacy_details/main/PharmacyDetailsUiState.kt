package com.example.pharmacy_details.main

import com.example.model.enums.ScreenState
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.util.UiText

data class PharmacyDetailsUiState(
    val pharmacyInfo: PharmacyDetailsResponse?=null,
    val pharmacyId: Int?=null,
    val profileScreenState: ScreenState= ScreenState.IDLE,

    val pharmacyAccessType: PharmacyAccessType?=null,
    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,
)
