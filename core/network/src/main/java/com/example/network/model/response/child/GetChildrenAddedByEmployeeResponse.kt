package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.Serializable

@Serializable
data class GetChildrenAddedByEmployeeResponse(
    val data: List<ChildDto>,
    val pagination: NetworkPagination
)
