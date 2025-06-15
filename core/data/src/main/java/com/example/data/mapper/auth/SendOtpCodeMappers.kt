package com.example.data.mapper.auth

import com.example.data.mapper.enums.toRoleDto
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.send_otp.SendOtpResponse
import com.example.network.model.request.auth.SendOtpRequestDto
import com.example.network.model.response.auth.SendOtpResponseDto

fun SendOtpRequest.toSendOtpRequestDto()=
    SendOtpRequestDto(
        email = this.email,
    )

fun  SendOtpResponseDto.toSendOtpResponse()=
    SendOtpResponse(
        message = this.message
    )