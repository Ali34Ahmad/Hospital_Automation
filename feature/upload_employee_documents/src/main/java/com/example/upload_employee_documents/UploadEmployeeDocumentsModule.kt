package com.example.upload_employee_documents

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadEmployeeDocumentsModule = module {
    viewModelOf(::UploadEmployeeDocumentsViewModel)
}