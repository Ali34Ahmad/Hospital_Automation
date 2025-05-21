package com.example.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AddAddressResponse (
    val updatedData: List<Int>
)