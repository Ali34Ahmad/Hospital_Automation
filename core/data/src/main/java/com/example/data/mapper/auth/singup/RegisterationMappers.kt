package com.example.data.mapper.auth.singup

import com.example.data.mapper.enums.toGender
import com.example.data.mapper.enums.toRole
import com.example.data.mapper.enums.toRoleDto
import com.example.model.auth.signup.RegistrationResponse
import com.example.model.auth.signup.SignUpCredentials
import com.example.model.auth.signup.UserData
import com.example.network.model.request.auth.signup.BaseRegistrationRequestDto
import com.example.network.model.response.auth.signup.BaseRegistrationResponseDto
import com.example.network.model.response.user.UserDataDto

fun SignUpCredentials.toRegistrationRequestDto(): BaseRegistrationRequestDto =
    BaseRegistrationRequestDto(
        role = this.role.toRoleDto(),
        email = this.email,
        firstName = this.firstName,
        middleName = this.middleName,
        lastName = this.lastName,
        password = this.password,
        phoneNumber = this.phoneNumber,
        gender = this.gender.toGender(),
    )

fun BaseRegistrationResponseDto.toRegistrationResponse(): RegistrationResponse =
    RegistrationResponse(
        data = this.data.toUserData()
    )

fun UserDataDto.toUserData(): UserData =
    UserData(
        userId = this.userId,
        role = this.role.toRole(),
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        verifiedResetPassword = this.verifiedResetPassword,
        verifiedAccount = this.verifiedAccount,
        phoneNumber = this.phoneNumber,
        addressGovernorate = this.addressGovernorate,
        addressCity = this.addressCity,
        addressRegion = this.addressRegion,
        addressStreet = this.addressStreet,
        addressNote = this.addressNote,
        specialty = this.specialty,
        imageUrl = this.imageUrl,
        medicalLicenseImgUrl = this.medicalLicenseImgUrl,
        gender = this.gender.toGender(),
        isSuspended = this.isSuspended,
        suspendingReason = this.suspendingReason,
        isResigned = this.isResigned,
        workStartDate = this.workStartDate,
        workEndDate = this.workEndDate,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        clinicId = this.clinicId,
        resignedBy = this.resignedBy,
        suspendedBy = this.suspendedBy,
        acceptedBy = this.acceptedBy
    )

