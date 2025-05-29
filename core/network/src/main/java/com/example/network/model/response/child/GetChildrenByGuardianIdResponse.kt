package com.example.network.model.response.child

import com.example.network.model.dto.child.ChildFullDto
import com.example.network.model.dto.user.UserDto
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetChildrenByGuardianResponseItem(
    @SerialName("Users_ChildrenId")
    val usersChildrenId: Int,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("child_id")
    val childId: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("employee_id")
    val employeeId: Int,
    val user: UserDto,
    val child: ChildFullDto
)

@Serializable
data class GetChildrenByGuardianIdResponse(
    val data: List<GetChildrenByGuardianResponseItem>
)