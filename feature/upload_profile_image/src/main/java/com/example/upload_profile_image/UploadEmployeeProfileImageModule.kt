package com.example.upload_profile_image

import com.example.upload_profile_image.main.UploadProfileImageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadProfileImageModule = module {
    viewModelOf(::UploadProfileImageViewModel)
}