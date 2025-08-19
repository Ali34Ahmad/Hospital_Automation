package com.example.network.model.request.vaccine

import kotlinx.serialization.Serializable

@Serializable
data class VaccinesIdsWrapper(
    val vaccinesIds: List<Int>?
)