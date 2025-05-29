package com.example.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AddAddressResponseDto (
    val updatedData: List<Int>
)