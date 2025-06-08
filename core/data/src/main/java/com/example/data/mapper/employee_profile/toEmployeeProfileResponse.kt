package com.example.data.mapper.employee_profile

import com.example.data.mapper.enums.toGender
import com.example.model.address.Address
import com.example.model.employee.EmployeeProfile
import com.example.model.employee.EmployeeProfileResponse
import com.example.model.enums.Role
import com.example.model.user.FullName
import com.example.network.model.response.employee.GetEmployeeByIdDto
import com.example.network.model.response.employee.GetEmployeeProfileByIdResponseDto
import com.example.network.utility.ApiRoutes

fun GetEmployeeProfileByIdResponseDto.toEmployeeProfileResponse(): EmployeeProfileResponse {
    return EmployeeProfileResponse(
        profile = this.data.toEmployeeProfile(),
        isAccessedByOwner = this.isAccessedByOwner,
    )
}

fun GetEmployeeByIdDto.toEmployeeProfile(): EmployeeProfile {
    return EmployeeProfile(
        userId = this.userId,
        role = Role.EMPLOYEE,
        email = this.email,
        fullName = FullName(
            firstName = this.firstName,
            middleName = this.middleName,
            lastName = this.lastName
        ),
        verifiedResetPassword =null ,
        verifiedAccount = null,
        phoneNumber = this.phoneNumber,
        address = Address(
            governorate = this.addressGovernorate,
            city = this.addressCity,
            region = this.addressRegion,
            street = this.addressStreet,
            note = this.addressNote
        ),
        imageUrl = "${ApiRoutes.BASE_URL}/${this.imageUrl}",
        documentsUrl = null,
        gender = this.gender?.toGender(),
        isSuspended = this.isSuspended,
        suspendingReason = null,
        isResigned = this.isResigned,
        createdAt = null,
        updatedAt = null,
        resignedBy = null,
        suspendedBy = null,
        acceptedBy = null
    )
}
