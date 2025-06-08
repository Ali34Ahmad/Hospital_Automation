package com.example.data.mapper.admin_profile

import com.example.data.mapper.enums.toGender
import com.example.model.admin_account.AdminProfile
import com.example.model.admin_account.AdminProfileResponse
import com.example.model.address.Address
import com.example.model.user.FullName
import com.example.network.model.response.AdminProfileDto
import com.example.network.model.response.AdminProfileResponseDto
import com.example.network.utility.ApiRoutes


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
        address = Address(
            governorate = this.addressGovernorate,
            city = this.addressCity,
            region = this.addressRegion,
            street = this.addressStreet,
            note = this.addressNote
        ),
        gender = this.gender?.toGender(),
        isSuspended = this.isSuspended,
        isResigned = this.isResigned,
        imageUrl = "${ApiRoutes.BASE_URL}/${this.imageUrl}",
    )
}
