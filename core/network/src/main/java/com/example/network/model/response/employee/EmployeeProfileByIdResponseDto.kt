package com.example.network.model.response.employee

import com.example.network.model.enums.GenderDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetEmployeeProfileByIdResponseDto(
    val profile: GetEmployeeByIdDto,
    @SerialName("is_The_Same_Employee")
    val isAccessedByOwner: Boolean=false,
)

@Serializable
data class GetEmployeeByIdDto(
    @SerialName("userId")
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
    val addressGovernorate: String?, // Marked as nullable
    @SerialName("address_city")
    val addressCity: String?,     // Marked as nullable
    @SerialName("address_region")
    val addressRegion: String?,   // Marked as nullable
    @SerialName("address_street")
    val addressStreet: String?,   // Marked as nullable
    @SerialName("address_note")
    val addressNote: String?,     // Marked as nullable
    val gender: GenderDto?,          // Assuming gender is a string from the API
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("is_resigned")
    val isResigned: Boolean,
    @SerialName("imgurl")
    val imageUrl: String?,
)