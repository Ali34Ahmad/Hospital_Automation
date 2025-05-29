package com.example.domain.di

import com.example.domain.use_cases.validator.ValidateConfirmPasswordUseCase
import com.example.domain.use_cases.validator.ValidateEmailUseCase
import com.example.domain.use_cases.validator.ValidateOtpUseCase
import com.example.domain.use_cases.validator.ValidatePasswordUseCase
import com.example.domain.use_cases.validator.ValidatePhoneNumberUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val validatorUseCasesModule= module {
    singleOf(::ValidateEmailUseCase)

    singleOf(::ValidateTextUseCase)

    singleOf(::ValidatePasswordUseCase)

    singleOf(::ValidatePhoneNumberUseCase)

    singleOf(::ValidateConfirmPasswordUseCase)

    singleOf(::ValidateOtpUseCase)
}