package com.example.network.model.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFullDto(
    @SerialName("userId")
    val userId: Int,
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
    val addressGovernorate: String?= null,
    @SerialName("address_city")
    val addressCity: String?= null,
    @SerialName("address_region")
    val addressRegion: String?= null,
    @SerialName("address_street")
    val addressStreet: String?= null,
    @SerialName("address_note")
    val addressNote: String?= null,
    @SerialName("gender")
    val gender: String?= null,
    @SerialName("imgurl")
    val imgUrl: String? = null,
)