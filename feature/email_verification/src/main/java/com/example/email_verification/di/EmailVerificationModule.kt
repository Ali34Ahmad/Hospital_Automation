package com.example.email_verification.di

import com.example.email_verification.email_verified_successfully.presentation.EmailVerifiedSuccessfullyViewModel
import com.example.email_verification.otp_verification.presentation.OtpVerificationViewModel
import com.example.email_verification.otp_verification.validator.OtpVerificationValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val emailVerificationModule = module {
    viewModelOf(::OtpVerificationViewModel)

    viewModelOf(::EmailVerifiedSuccessfullyViewModel)

    singleOf(::OtpVerificationValidator)
}