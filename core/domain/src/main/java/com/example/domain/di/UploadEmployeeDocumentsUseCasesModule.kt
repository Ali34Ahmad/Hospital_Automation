package com.example.domain.di

import com.example.domain.use_cases.upload_employee_documents.UploadEmployeeDocumentsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uploadEmployeeDocumentsUseCasesModule= module {
    singleOf(::UploadEmployeeDocumentsUseCase)
}