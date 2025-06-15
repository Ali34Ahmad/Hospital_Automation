package com.example.guardians_search.presentation

import com.example.model.enums.ScreenState


data class GuardiansSearchUiState(
    val childId: Int? = null,
    val searchQuery: String = "",
    val screenState: ScreenState = ScreenState.IDLE,
    val isRefreshing: Boolean = false,
)
