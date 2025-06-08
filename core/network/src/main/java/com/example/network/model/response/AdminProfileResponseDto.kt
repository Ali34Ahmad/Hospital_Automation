package com.example.network.model.response

import com.example.network.model.enums.GenderDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminProfileResponseDto(
    val admin: AdminProfileDto
)

@Serializable
data class AdminProfileDto(
    val userId: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("middle_name")
    val middleName: String?,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("address_governorate")
    val addressGovernorate: String?,
    @SerialName("address_city")
    val addressCity: String?,
    @SerialName("address_region")
    val addressRegion: String?,
    @SerialName("address_street")
    val addressStreet: String?,
    @SerialName("address_note")
    val addressNote: String?,
    val gender: GenderDto?,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("is_resigned")
    val isResigned: Boolean,
    @SerialName("imgurl")
    val imageUrl: String?,
)