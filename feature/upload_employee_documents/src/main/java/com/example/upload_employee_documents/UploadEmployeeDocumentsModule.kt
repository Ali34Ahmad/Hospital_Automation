package com.example.upload_employee_documents

import com.example.upload_employee_documents.main.UploadEmployeeDocumentsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadEmployeeDocumentsModule = module {
    viewModelOf(::UploadEmployeeDocumentsViewModel)
}