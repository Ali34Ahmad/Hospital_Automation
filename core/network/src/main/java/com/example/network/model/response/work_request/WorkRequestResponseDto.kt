package com.example.network.model.response.work_request

import com.example.network.model.dto.work_request.WorkRequestDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WorkRequestResponseDto(
    @SerialName("myrequest")
    val request: WorkRequestDto
)
