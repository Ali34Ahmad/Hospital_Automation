package com.example.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressRequestDto(
    @SerialName("address_governorate")
    val governorate: String,
    @SerialName("address_city")
    val city: String,
    @SerialName("address_region")
    val region: String,
    @SerialName("address_street")
    val street: String,
    @SerialName("address_note")
    val note: String? = null
)