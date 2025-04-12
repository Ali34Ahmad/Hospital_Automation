package com.example.network.model.response

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@OptIn(InternalSerializationApi::class)
@Serializable
data class ShowChildProfileResponse(
    val child: NetworkChild,
    @SerialName("numOfGuardian")
    val numberOfGuardians: Int,
)