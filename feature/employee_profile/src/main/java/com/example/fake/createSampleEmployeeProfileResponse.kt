package com.example.fake

import com.example.model.address.Address
import com.example.model.employee.EmployeeProfile
import com.example.model.employee.EmployeeProfileResponse
import com.example.model.enums.Gender
import com.example.model.enums.Role
import com.example.model.user.FullName

fun createSampleEmployeeProfileResponse(): List<EmployeeProfileResponse> {
    val sampleProfile = EmployeeProfile(
        userId = 101,
        role = Role.EMPLOYEE,
        email = "jane.doe@example.com",
        fullName = FullName(
            firstName = "Jane",
            lastName = "Doe",
            middleName = "Anne"
        ),
        verifiedResetPassword = true,
        verifiedAccount = true,
        phoneNumber = "0987654321",
        address = Address(
            governorate = "California",
            city = "Mountain View",
            region = "Silicon Valley",
            street = "1600 Amphitheatre Parkway",
            note = "Near the big colorful sign"
        ),
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

    return listOf(
        EmployeeProfileResponse(profile = sampleProfile, isAccessedByOwner = true),
        EmployeeProfileResponse(profile = sampleProfile, isAccessedByOwner = false),
    )
}