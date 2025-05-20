package com.example.network.model.response.user

import com.example.network.model.dto.user.UserFullDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable


@OptIn(InternalSerializationApi::class)
@Serializable
data class GetUsersByNameResponse(
    val data : List<UserFullDto>,
    val pagination : NetworkPagination
)
