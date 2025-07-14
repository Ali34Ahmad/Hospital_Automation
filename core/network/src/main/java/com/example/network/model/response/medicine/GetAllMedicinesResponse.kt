package com.example.network.model.response.medicine

import com.example.network.model.dto.medicine.MedicineDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.Serializable


@Serializable
data class GetAllMedicinesResponse(
    val data: List<MedicineDto>,
    val pagination: NetworkPagination,
)
