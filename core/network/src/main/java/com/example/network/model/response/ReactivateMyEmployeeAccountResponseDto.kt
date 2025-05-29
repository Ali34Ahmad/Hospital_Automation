package com.example.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ReactivateMyEmployeeAccountResponseDto(
    val updatedData:List<Int>
)
