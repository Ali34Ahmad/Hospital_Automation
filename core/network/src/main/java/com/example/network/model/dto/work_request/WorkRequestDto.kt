package com.example.network.model.dto.work_request

import com.example.network.model.enums.RequestStateDto
import com.example.network.model.enums.RequestTypeDto
import com.example.network.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class WorkRequestDto(
    val requestId: Int,
    @SerialName("request_type")
    val requestType : RequestTypeDto,
    val state: RequestStateDto,
    @SerialName("request_data_time")
    @Serializable(with = LocalDateTimeSerializer::class)
    val requestDateTime: LocalDateTime,
    @SerialName("employee_id")
    val employeeId: Int,
    @SerialName("clinic_id")
    val clinicId: Int,
    val createdAt: String,
    val updatedAt: String
)