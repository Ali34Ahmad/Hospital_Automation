package com.example.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DeactivateMyEmployeeAccountResponse(
    val updatedData:List<Int>?=null
)
