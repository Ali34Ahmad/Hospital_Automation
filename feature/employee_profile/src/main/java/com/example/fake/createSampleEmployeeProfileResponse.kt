package com.example.fake

import com.example.network.constants.Gender
import com.example.network.model.response.EmployeeProfile
import com.example.network.model.response.EmployeeProfileResponse

fun createSampleEmployeeProfileResponse(): EmployeeProfileResponse {
    val sampleProfile = EmployeeProfile(
        userId = 101,
        role = "developer",
        email = "jane.doe@example.com",
        firstName = "Jane",
        lastName = "Doe",
        middleName = "Anne",
        verifiedResetPassword = true,
        verifiedAccount = true,
        phoneNumber = "0987654321",
        addressGovernorate = "California",
        addressCity = "Mountain View",
        addressRegion = "Silicon Valley",
        addressStreet = "1600 Amphitheatre Parkway",
        addressNote = "Near the big colorful sign",
        imageUrl = "https://example.com/images/jane_doe.jpg",
        documentsUrl = "https://example.com/documents/jane_doe_license.pdf",
        gender = Gender.FEMALE,
        isSuspended = false,
        suspendingReason = null,
        isResigned = false,
        createdAt = "2023-01-15T10:30:00Z", // ISO 8601 format
        updatedAt = "2024-05-15T14:45:10Z", // ISO 8601 format
        resignedBy = null,
        suspendedBy = null,
        acceptedBy = 12 // Example: ID of an admin who accepted
    )

    return EmployeeProfileResponse(profile = sampleProfile)
}