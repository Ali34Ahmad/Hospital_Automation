package com.example.pharmacy_details.presentation

import com.example.model.enums.ScreenState
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.util.UiText
import com.example.utility.network.Error

data class PharmacyDetailsUiState(
    val pharmacyInfo: PharmacyDetailsResponse?=null,
    val pharmacyId: Int?=null,
    val profileScreenState: ScreenState= ScreenState.IDLE,

    val pharmacyAccessType: PharmacyAccessType?=null,
    val isRefreshing: Boolean=false,
    val toastMessage: UiText?=null,


    val isAccountDeactivatedSuccessfully: Boolean = false,
    val accountDeactivationError: Error? = null,

    val showLoadingDialog: Boolean = false,
    val loadingDialogText: UiText? = null,

    val showErrorDialog: Boolean = false,
    val errorDialogText: UiText? = null,

    )
