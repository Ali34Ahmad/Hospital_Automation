package com.example.data.mapper.auth.singup

import com.example.data.mapper.enums.toGender
import com.example.data.mapper.enums.toRoleDto
import com.example.model.auth.signup.BaseRegistrationRequest
import com.example.model.auth.signup.BaseRegistrationResponse
import com.example.model.auth.signup.RegistrationResponse
import com.example.model.auth.signup.SignUpCredentials
import com.example.network.model.request.auth.signup.BaseRegistrationRequestDto
import com.example.network.model.response.auth.signup.BaseRegistrationResponseDto

fun BaseRegistrationRequest.toBaseRegistrationRequestDto(): BaseRegistrationRequestDto =
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

fun BaseRegistrationResponseDto.toBaseRegistrationResponse(): BaseRegistrationResponse =
    BaseRegistrationResponse(
        data = this.data.toUserData()
    )