package com.example.upload_employee_documents

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uploadEmployeeDocumentsModule = module {
    viewModel {
        UploadEmployeeDocumentsViewModel(
            get()
        )
    }
}