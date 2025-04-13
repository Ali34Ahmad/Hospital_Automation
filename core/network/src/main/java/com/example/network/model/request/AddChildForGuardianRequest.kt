package com.example.network.model.request

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@OptIn(InternalSerializationApi::class)
@Serializable
data class AddChildForGuardianRequest(
    @SerialName("child-id")
    val childId: Int,
    @SerialName("guardian-id")
    val guardianId: Int
)
