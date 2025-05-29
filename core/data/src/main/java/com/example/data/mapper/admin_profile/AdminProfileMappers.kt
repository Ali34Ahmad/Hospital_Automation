package com.example.data.mapper.admin_profile

import com.example.data.mapper.enums.toGender
import com.example.model.admin_account.AdminProfile
import com.example.model.admin_account.AdminProfileResponse
import com.example.model.user.FullName
import com.example.network.model.response.AdminProfileDto
import com.example.network.model.response.AdminProfileResponseDto


fun AdminProfileResponseDto.toAdminProfileResponse(): AdminProfileResponse {
    return AdminProfileResponse(
        admin = this.admin.toAdminProfile()
    )
}

fun AdminProfileDto.toAdminProfile(): AdminProfile {
    return AdminProfile(
        userId = this.userId,
        email = this.email,
        fullName = FullName(
            firstName = this.firstName,
            middleName = this.middleName,
            lastName = this.lastName,
        ),
        phoneNumber = this.phoneNumber,
        addressGovernorate = this.addressGovernorate,
        addressCity = this.addressCity,
        addressRegion = this.addressRegion,
        addressStreet = this.addressStreet,
        addressNote = this.addressNote,
        gender = this.gender?.toGender(), // Using the GenderDto to Gender mapper
        isSuspended = this.isSuspended,
        isResigned = this.isResigned
    )
}
