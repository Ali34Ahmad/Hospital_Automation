package com.example.network.model.response.prescription

import com.example.network.model.dto.prescription.PrescriptionDto
import kotlinx.serialization.Serializable

@Serializable
data class AddPrescriptionResponse(
    val prescription : PrescriptionDto
)
