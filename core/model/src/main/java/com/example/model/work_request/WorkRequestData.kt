package com.example.model.work_request

import java.time.LocalDateTime

data class WorkRequestData(
    val requestId: Int,
    val requestType : RequestType,
    val state: RequestState,
    val requestDateTime: LocalDateTime,
    val employeeId: Int,
    val clinicId: Int,
)

