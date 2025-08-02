package com.example.network.model.request.child

import com.example.network.serializer.LocalDateSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@OptIn(InternalSerializationApi::class)
@Serializable
data class AddChildRequest(
    @SerialName("first_name") val firstName: String,
    @SerialName("father_first_name") val fatherFirstName: String,
    @SerialName("father_last_name") val fatherLastName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("mother_first_name") val motherFirstName: String,
    @SerialName("mother_last_name") val motherLastName: String,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("date_of_birth") val dateOfBirth: LocalDate,
    val gender: String
)