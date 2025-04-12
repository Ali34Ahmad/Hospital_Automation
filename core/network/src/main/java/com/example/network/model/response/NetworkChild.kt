package com.example.network.model.response

import com.example.network.model.request.NetworkFullName
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class NetworkChild(
    @SerialName("childrenId") val childId: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("father_first_name") val fatherFirstName: String,
    @SerialName("father_last_name") val fatherLastName: String,
    @SerialName("mother_first_name") val motherFirstName: String,
    @SerialName("mother_last_name") val motherLastName: String,
    @SerialName("date_of_birth") val dateOfBirth: String,
    @SerialName("gender") val gender: String,
    @SerialName("birth_certificate_img_url") val birthCertificateImgUrl: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("employee_id") val employeeId: Int,
    val user: NetworkFullName? = null,
)
