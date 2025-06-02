package com.example.email_verification

import com.example.email_verification.email_verified_successfully.main.EmailVerifiedSuccessfullyViewModel
import com.example.email_verification.otp_verification.main.OtpVerificationViewModel
import com.example.email_verification.otp_verification.validator.OtpVerificationValidator
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val emailVerificationModule = module {
    viewModelOf(::OtpVerificationViewModel)

    viewModelOf(::EmailVerifiedSuccessfullyViewModel)

    singleOf(::OtpVerificationValidator)
}