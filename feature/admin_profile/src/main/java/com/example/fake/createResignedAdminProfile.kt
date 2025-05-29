package com.example.fake

import com.example.model.admin_account.AdminProfile
import com.example.model.admin_account.AdminProfileResponse
import com.example.model.enums.Gender
import com.example.model.user.FullName
import com.example.network.model.response.AdminProfileDto

fun createSampleAdminProfile(): AdminProfileResponse {
    return AdminProfileResponse(
        AdminProfile(
            userId = 303,
            email = "jane.roe.former@example.com",
            FullName(
                firstName = "Jane",
                middleName = "X.",
                lastName = "Roe"
            ),
            phoneNumber = "+1-555-0199",
            addressGovernorate = "California",
            addressCity = "San Francisco",
            addressRegion = "Mission District",
            addressStreet = "123 Main St",
            addressNote = "Resigned on good terms.",
            gender = Gender.MALE,
            isSuspended = false,
            isResigned = true // Example of a resigned admin)
        )
    )
}