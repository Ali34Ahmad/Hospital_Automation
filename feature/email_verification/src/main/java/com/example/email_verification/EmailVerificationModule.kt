package com.example.email_verification

import com.example.email_verification.email_verified_successfully.EmailVerifiedSuccessfullyViewModel
import com.example.email_verification.otp_verification.OtpVerificationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val emailVerificationModule = module {
    viewModel {
        OtpVerificationViewModel(
            get(),
            get()
        )
    }
    viewModel {
        EmailVerifiedSuccessfullyViewModel(
            get(),
            get()
        )
    }

}