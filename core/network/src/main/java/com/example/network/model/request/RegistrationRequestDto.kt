package com.example.network.model.request

import android.annotation.SuppressLint
import com.example.network.model.enums.GenderDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegistrationRequestDto(
    val role: String,
    val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("middle_name") val middleName: String,
    @SerialName("last_name") val lastName: String,
    val password: String,
    @SerialName("phone_number") val phoneNumber: String,
    val gender: GenderDto,
)