package com.example.upload_employee_profile_image

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uploadEmployeeProfileImageModule = module {
    viewModel {
        UploadEmployeeProfileImageViewModel(
            get()
        )
    }
}