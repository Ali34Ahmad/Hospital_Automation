package com.example.network.model.response

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class NetworkUser(
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("middle_name")
    val middleName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("address_governorate")
    val addressGovernorate: String,
    @SerialName("address_city")
    val addressCity: String,
    @SerialName("address_region")
    val addressRegion: String,
    @SerialName("address_street")
    val addressStreet: String,
    @SerialName("address_note")
    val addressNote: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("imgurl")
    val imgUrl: String?
)
