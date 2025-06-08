package com.example.fake

import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.employment_history.UserDetails
import com.example.model.employment_history.UserReference
import com.example.model.user.FullName
import java.time.LocalDate

fun createSampleEmploymentHistoryResponse(): EmploymentHistoryResponse {
    val sampleResignedByManager = UserReference(
        userId = 1,
        fullName = FullName(
            firstName = "Elias",
            middleName = "Philip",
            lastName = "Fares"
        )
    )

    val sampleSuspendedByManager = UserReference(
        userId = 7,
        fullName = FullName(
            firstName = "Elias",
            middleName = "Philip",
            lastName = "Fares"
        )
    )

    val sampleAcceptedByManager = UserReference(
        userId = 3,
        fullName = FullName(
            firstName = "Elias",
            middleName = "Philip",
            lastName = "Fares"
        )
    )

    val sampleCurrentUser = UserDetails(
        userId = 6,
        fullName = FullName("Mariam", "Elias", "Saoud"),
        isResigned = true,
        acceptedBy = sampleAcceptedByManager.userId,
        suspendedBy = sampleSuspendedByManager.userId,
        resignedBy = sampleResignedByManager.userId,
        imageUrl = "",
        workStartDate = LocalDate.of(2020, 12, 2),
        workEndDate = LocalDate.of(2022, 12, 2),
        documentsFileUrl = null
    )

    return EmploymentHistoryResponse(
        currentUser = sampleCurrentUser,
        resignedBy = sampleResignedByManager,
        suspendedBy = sampleSuspendedByManager,
        acceptedBy = sampleAcceptedByManager,
        employeeDocumentsFileSize = 1214525
    )
}