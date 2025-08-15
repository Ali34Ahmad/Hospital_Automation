package com.example.network.model.response.doctor

import com.example.network.model.dto.user.UserDto
import com.example.network.model.response.NetworkPagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetEmployeesResponse (
    val data: List<UserDto>,
    @SerialName("number_of_employed_doctors")
    val employedCount: Int,
    @SerialName("number_of_stopped_doctors")
    val stoppedCount: Int,
    @SerialName("number_of_resigned_doctors")
    val resignedCount: Int,
    val pagination: NetworkPagination
)