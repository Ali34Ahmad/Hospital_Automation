package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

/**
 * You can use this class to represent any response
 * that return children filtered by name.
 * @author Ali Mansoura
 */
@OptIn(InternalSerializationApi::class)
@Serializable
data class GetChildrenByNameResponse(
    val data: List<ChildDto>,
    val pagination: NetworkPagination
)
