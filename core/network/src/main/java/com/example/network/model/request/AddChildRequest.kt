package com.example.network.model.request

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class AddChildRequest(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("father_first_name")
    val fatherName: String,
    @SerialName("father_last_name")
    val fatherLastName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("mother_first_name")
    val motherFirstName: String,
    @SerialName("mother_last_name")
    val motherLastName: String,
    @SerialName("date_of_birth")
    val dateOfBirth: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("guardian_first_name")
    val guardianFirstName: String,
    @SerialName("guardian_last_name")
    val guardianLastName: String,
    @SerialName("guardian_middle_name")
    val guardianMiddleName: String,
)