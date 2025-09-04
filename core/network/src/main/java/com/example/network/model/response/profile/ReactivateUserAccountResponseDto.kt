package com.example.network.model.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class ReactivateUserAccountResponseDto(
    val updatedData:List<Int>
)
