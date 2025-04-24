package com.example.network.model.response

import kotlinx.serialization.SerialName

data class RegistrationResponse(
    @SerialName("is_resigned")
    val isResigned: Boolean,
    @SerialName("userId")
    val userId: Int,
    val role: String,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("middle_name")
    val middleName: String?, // Can be null
    val password: String, // Note: You might not always want to expose the password in the response
    @SerialName("phone_number")
    val phoneNumber: String,
    val gender: String,
    val updatedAt: String,
    val createdAt: String
)
