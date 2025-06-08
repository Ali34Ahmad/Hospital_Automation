package com.example.data.mapper.employee_profile

import com.example.data.mapper.enums.toGender
import com.example.model.address.Address
import com.example.model.employee.EmployeeProfile
import com.example.model.employee.EmployeeProfileResponse
import com.example.model.user.FullName
import com.example.network.model.response.EmployeeProfileDto
import com.example.network.model.response.EmployeeProfileResponseDto
import com.example.network.utility.ApiRoutes


fun EmployeeProfileResponseDto.toEmployeeProfileResponse(): EmployeeProfileResponse =
    EmployeeProfileResponse(
        profile = this.profile.toEmployeeProfile(),
        isAccessedByOwner = true,
    )

fun EmployeeProfileDto.toEmployeeProfile(): EmployeeProfile =
    EmployeeProfile(
        userId = this.userId,
        role = this.role,
        email = this.email,
        fullName = FullName(
            firstName = this.firstName,
            lastName = this.lastName,
            middleName = this.middleName
        ),
        verifiedResetPassword = this.verifiedResetPassword,
        verifiedAccount = this.verifiedAccount,
        phoneNumber = this.phoneNumber,
        address = Address(
            governorate = this.addressGovernorate,
            city = this.addressCity,
            region = this.addressRegion,
            street = this.addressStreet,
            note = this.addressNote
        ),
        imageUrl = "${ApiRoutes.BASE_URL}/${this.imageUrl}",
        documentsUrl = this.documentsUrl,
        gender = this.gender?.toGender(),
        isSuspended = this.isSuspended,
        suspendingReason = this.suspendingReason,
        isResigned = this.isResigned,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        resignedBy = this.resignedBy,
        suspendedBy = this.suspendedBy,
        acceptedBy = this.acceptedBy
    )