package com.example.network.model.response.child

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class UploadCertificatedResponse(
    val updatedCertificate: String
)