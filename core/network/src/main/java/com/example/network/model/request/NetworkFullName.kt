package com.example.network.model.request

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)

@Serializable
data class NetworkFullName(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("fatherOrmiddle_name")
    val middleName: String,
    @SerialName("last_name")
    val lastName: String
)
