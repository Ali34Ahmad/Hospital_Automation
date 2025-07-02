package com.example.network.model.request.appointment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddDiagnosisRequest(
    @SerialName("patient_diagnosis")
    val diagnosis: String,
)
