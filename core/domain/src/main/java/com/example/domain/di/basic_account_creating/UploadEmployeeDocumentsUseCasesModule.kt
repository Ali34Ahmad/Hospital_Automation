package com.example.domain.di.basic_account_creating

import com.example.domain.use_cases.upload_employee_documents.UploadEmploymentDocumentsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uploadEmploymentDocumentsUseCasesModule= module {
    singleOf(::UploadEmploymentDocumentsUseCase)
}