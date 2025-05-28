package com.example.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPagination(
    val page: Int,
    val limit: Int,
    @SerialName("totalpage")
    val totalPages: Int,
)
