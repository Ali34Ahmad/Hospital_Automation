package com.example.model

import com.example.constants.enums.NotificationType
import com.example.model.work_request.SingleRequestResponse
import java.time.LocalDateTime

data class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val userId: Int? = null,
    val request: SingleRequestResponse? = null,
    val appointmentId: Int? = null,
    val vaccineId: Int? = null,
    val pharmacistId: Int? = null,
    val sendTime:LocalDateTime,
    val type: NotificationType?=null,
)