package com.example.upload_child_documents

import com.example.upload_child_documents.main.UploadChildDocumentsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uploadEmployeeDocumentsModule = module {
    viewModelOf(::UploadChildDocumentsViewModel)
}