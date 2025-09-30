package com.example.upload_child_documents.di

import com.example.upload_child_documents.presentation.UploadChildDocumentsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadChildDocumentsModule = module {
    viewModelOf(::UploadChildDocumentsViewModel)
}