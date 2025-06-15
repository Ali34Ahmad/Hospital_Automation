package com.example.network.model.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class DeactivateMyEmployeeAccountResponseDto(
    val updatedData:List<Int>
)
