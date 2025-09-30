package com.example.upload_employee_documents.di

import com.example.upload_employee_documents.presentation.UploadEmployeeDocumentsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadEmploymentDocumentsModule = module {
    viewModelOf(::UploadEmployeeDocumentsViewModel)
}