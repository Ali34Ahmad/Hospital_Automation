package com.example.upload_profile_image.di

import com.example.upload_profile_image.presentation.UploadProfileImageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadProfileImageModule = module {
    viewModelOf(::UploadProfileImageViewModel)
}