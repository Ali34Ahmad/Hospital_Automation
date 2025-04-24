package com.example.ui.fake

import com.example.constants.enums.RequestState
import com.example.constants.enums.RequestType
import com.example.model.Request
import java.time.LocalDateTime

fun createSampleRequests(): List<Request> {
    return listOf(
        Request(
            id = 1,
            employeeId = 101,
            employeeName = "Ali Ahmad",
            requestType = RequestType.DOCTOR,
            state = RequestState.PENDING,
            clinicId = 201,
            clinicName = "Department of mental illnesses",
            requestingDateTime = LocalDateTime.of(2024, 10, 26, 9, 0),
        ),
        Request(
            id = 2,
            employeeId = 102,
            employeeName = "Ali Ahmad",
            requestType = RequestType.DOCTOR,
            state = RequestState.EXPIRED,
            clinicId = 202,
            clinicName = "Department of mental illnesses",
            requestingDateTime = LocalDateTime.of(2024, 10, 20, 14, 30),
            responseDateTime = LocalDateTime.of(2024, 10, 22, 10, 0)
        ),
        Request(
            id = 3,
            employeeId = 103,
            employeeName = "Ali Ahmad",
            requestType = RequestType.DOCTOR,
            state = RequestState.REJECTED,
            clinicId = 201,
            clinicName = "Department of mental illnesses",
            requestingDateTime = LocalDateTime.of(2024, 10, 15, 11, 15),
            responseDateTime = LocalDateTime.of(2024, 10, 18, 16, 45)
        )
    )
}