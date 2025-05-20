package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class GetChildrenByNameResponse(
    val data: List<ChildDto>,
    val pagination: NetworkPagination
)
