package com.example.pharmacy_medicines.presentation

import com.example.model.enums.ScreenState
import com.example.model.enums.TopBarState

data class PharmacyMedicinesUIState(
    val pharmacyId: Int,
    val title: String,
    val imageUrl: String,
    val screenState: ScreenState = ScreenState.IDLE,
    val topBarState: TopBarState = TopBarState.DEFAULT,
    val query: String = "",
    val isRefreshing: Boolean = false,
)