package com.example.network.model.response.medicine

import com.example.network.model.dto.medicine.MedicineDto
import kotlinx.serialization.Serializable


@Serializable
data class GetAllMedicinesResponse(
    val data: List<MedicineDto>
)
