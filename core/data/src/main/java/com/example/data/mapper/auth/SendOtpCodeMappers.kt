package com.example.data.mapper.auth

import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.send_otp.SendOtpResponse
import com.example.network.model.request.SendOtpRequestDto
import com.example.network.model.response.SendOtpResponseDto

fun SendOtpRequest.toSendOtpRequestDto()=
    SendOtpRequestDto(
        email = this.email
    )

fun  SendOtpResponseDto.toSendOtpResponse()=
    SendOtpResponse(
        message = this.message
    )