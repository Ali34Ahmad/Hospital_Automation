package com.example.domain.di.auth

import com.example.domain.use_cases.auth.LoginUseCase
import com.example.domain.use_cases.auth.LogoutUseCase
import com.example.domain.use_cases.auth.ResetPasswordUseCase
import com.example.domain.use_cases.auth.SendOtpToEmailUseCase
import com.example.domain.use_cases.auth.VerifyEmailUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authUseCasesModule=module{
    singleOf(::VerifyEmailUseCase)

    singleOf(::SendOtpToEmailUseCase)

    singleOf(::LoginUseCase)

    singleOf(::LogoutUseCase)

    singleOf(::ResetPasswordUseCase)
}