package com.example.network.model.request.auth.signup

import com.example.network.model.enums.GenderDto
import com.example.network.model.enums.RoleDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseRegistrationRequestDto(
    val role: RoleDto,
    val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("middle_name") val middleName: String,
    @SerialName("last_name") val lastName: String,
    val password: String,
    @SerialName("phone_number") val phoneNumber: String,
    val gender: GenderDto,
)