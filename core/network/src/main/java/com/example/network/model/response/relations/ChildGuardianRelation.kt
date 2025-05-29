package com.example.network.model.response.relations

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChildGuardianRelationResponse(
    @SerialName("child_Guardiant_relation")
    val childGuardianRelation: ChildGuardianRelation
)

@OptIn(InternalSerializationApi::class)
@Serializable
data class ChildGuardianRelation(
    @SerialName("Users_ChildrenId")
    val usersChildrenId: Int,

    @SerialName("employee_id")
    val employeeId: Int,

    @SerialName("child_id")
    val childId: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("updatedAt")
    val updatedAt: String,

    @SerialName("createdAt")
    val createdAt: String
)
