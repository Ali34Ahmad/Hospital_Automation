package com.example.vaccine_details_screen.presentation

import com.example.model.enums.ScreenState
import com.example.model.vaccine.VaccineData
import com.example.util.UiText
import com.example.vaccine_details_screen.navigation.VaccinePreviousScreen

data class VaccineDetailsUiState(
    val vaccine: VaccineData? = null,
    val vaccineId: Int? = null,
    val vaccinePreviousScreen: VaccinePreviousScreen? = null,

    val screenState: ScreenState = ScreenState.IDLE,

    val isRefreshing: Boolean = false,
    val toastMessage: UiText? = null,
)