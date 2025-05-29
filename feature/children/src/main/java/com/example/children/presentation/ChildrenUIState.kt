package com.example.children.presentation

import com.example.model.child.ChildFullData
import com.example.model.enums.FetchingDataState
import com.example.model.helper.IdType

data class ChildrenUIState(
    val userChildren: List<ChildFullData> = emptyList(),
    val fetchingDataState: FetchingDataState = FetchingDataState.DOING_PROCESS,
    val type: IdType
)