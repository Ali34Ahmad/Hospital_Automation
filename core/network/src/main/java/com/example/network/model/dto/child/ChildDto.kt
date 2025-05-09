package com.example.network.model.dto.child

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class ChildDto(
    @SerialName("childId") val childId: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("father_first_name") val fatherFirstName: String,
    @SerialName("father_last_name") val fatherLastName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("mother_first_name") val motherFirstName: String,
    @SerialName("mother_last_name") val motherLastName: String,
    @SerialName("date_of_birth") val dateOfBirth: String,
    val gender: String,
    @SerialName("employee_id") val employeeId: Int,
    val updatedAt: String,
    val createdAt: String
)