package com.example.data.mapper.employee

import com.example.data.mapper.enums.toGender
import com.example.model.employee.EmployeeProfile
import com.example.model.employee.EmployeeProfileResponse
import com.example.model.user.FullName
import com.example.network.model.response.EmployeeProfileDto
import com.example.network.model.response.EmployeeProfileResponseDto


fun EmployeeProfileResponseDto.toEmployeeProfileResponse(): EmployeeProfileResponse =
    EmployeeProfileResponse(
        profile = this.profile.toEmployeeProfile()
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
        addressGovernorate = this.addressGovernorate,
        addressCity = this.addressCity,
        addressRegion = this.addressRegion,
        addressStreet = this.addressStreet,
        addressNote = this.addressNote,
        imageUrl = this.imageUrl,
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