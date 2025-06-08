package com.example.domain.di

import com.example.domain.use_cases.upload_child_documents.UploadChildDocumentsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uploadChildDocumentsUseCase= module {
    singleOf(::UploadChildDocumentsUseCase)
}