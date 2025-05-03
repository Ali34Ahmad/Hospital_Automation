package com.example.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressRequest(
    @SerialName("address_governorate")
    val addressGovernorate: String,
    @SerialName("address_city")
    val addressCity: String,
    @SerialName("address_region")
    val addressRegion: String,
    @SerialName("address_street")
    val addressStreet: String,
    @SerialName("address_note")
    val addressNote: String? = null
)