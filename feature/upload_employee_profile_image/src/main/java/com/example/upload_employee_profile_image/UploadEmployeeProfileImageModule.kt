package com.example.upload_employee_profile_image

import com.example.upload_employee_profile_image.main.UploadEmployeeProfileImageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadEmployeeProfileImageModule = module {
    viewModelOf(::UploadEmployeeProfileImageViewModel)
}