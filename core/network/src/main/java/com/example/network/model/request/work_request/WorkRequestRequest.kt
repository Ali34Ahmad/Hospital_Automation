package com.example.network.model.request.work_request

import com.example.network.model.enums.RequestTypeDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkRequestRequest(
    @SerialName("request_type")
    val requestType: RequestTypeDto,
)