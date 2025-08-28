package com.example.network.model.response.work_request

import com.example.network.model.dto.work_request.WorkRequestDto
import com.example.network.model.enums.RequestStateDto
import com.example.network.model.enums.RequestTypeDto
import com.example.network.model.response.user.UserMainInfoDto
import com.example.network.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class SingleRequestResponseDto(
    @SerialName("requestId")
    val requestId: Int,

    @SerialName("request_type")
    val requestType: RequestTypeDto,

    @SerialName("state")
    val state: RequestStateDto,

    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("request_data_time")
    val requestDataTime: LocalDateTime,

    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("response_date_time")
    val responseDateTime: LocalDateTime?,

    @SerialName("clinic_id")
    val clinicId: Int? = null,

    @SerialName("employee_id")
    val employeeId: Int,

    @SerialName("user")
    val user: UserMainInfoDto,

    @SerialName("clinic")
    val clinic: ClinicMainInfoDto? = null
)

@Serializable
data class ClinicMainInfoDto(
    val clinicId:Int,
    val name:String,
)