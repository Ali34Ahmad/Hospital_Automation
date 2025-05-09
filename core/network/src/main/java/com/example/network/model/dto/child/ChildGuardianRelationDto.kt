package com.example.network.model.dto.child

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class ChildGuardianRelationDto(
    @SerialName("Users_ChildrenId") val usersChildrenId: Int,
    @SerialName("user_id") val userId: String,
    @SerialName("employee_id") val employeeId: Int,
    val updatedAt: String,
    val createdAt: String
)
