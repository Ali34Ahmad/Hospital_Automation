package com.example.network.model.request

import android.annotation.SuppressLint
import com.example.network.constants.Gender
import com.example.network.constants.Role
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegistrationRequest(
    val role: String,
    val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("middle_name") val middleName: String,
    @SerialName("last_name") val lastName: String,
    val password: String,
    @SerialName("phone_number") val phoneNumber: String,
    val gender: Gender,
)