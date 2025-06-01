package com.example.guardians_search.presentation

import com.example.model.enums.FetchingDataState


data class GuardiansSearchUiState(
    val childId: Int? = null,
    val searchQuery: String = "",
    val fetchingDataState: FetchingDataState = FetchingDataState.IDLE
)
