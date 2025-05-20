package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildFullDto
import com.example.network.model.dto.user.UserDto
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class GetChildrenByGuardianResponseItem(
    @SerialName("Users_ChildrenId")
    val usersChildrenId: Int,
    val createdAt: String,
    val updatedAt: String,
    val childId: Int,
    val userId: Int,
    val employeeId: Int,
    val user: UserDto,
    val child: ChildFullDto
)

@OptIn(InternalSerializationApi::class)
@Serializable
data class GetChildrenByGuardianIdResponse(
    val data: List<GetChildrenByGuardianResponseItem>
)