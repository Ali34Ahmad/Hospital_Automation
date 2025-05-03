package com.example.network.model.request

import kotlinx.serialization.SerialName

data class RegistrationRequest(
    val role: String,
    val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("middle_name") val middleName: String? = null,
    val password: String,
    @SerialName("phone_number") val phoneNumber: String,
    // TODO: Use Gender
//    val gender: Gender
    val gender: String
)