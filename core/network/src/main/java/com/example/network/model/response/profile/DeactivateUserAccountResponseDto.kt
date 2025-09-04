package com.example.network.model.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class DeactivateUserAccountResponseDto(
    val updatedData:List<Int>
)
