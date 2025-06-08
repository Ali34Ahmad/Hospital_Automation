package com.example.fake

import com.example.model.address.Address
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
            address = Address(
                governorate = "California",
                city = "San Francisco",
                region = "Mission District",
                street = "123 Main St",
                note = "Resigned on good terms."
            ),
            gender = Gender.MALE,
            isSuspended = false,
            isResigned = true ,
            imageUrl = ""
        )
    )
}