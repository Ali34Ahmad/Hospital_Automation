package com.example.domain.di.children

import com.example.domain.use_cases.children.AddChildUseCase
import com.example.domain.use_cases.children.GetChildByIdUseCase
import com.example.domain.use_cases.children.GetChildrenByGuardianIdUseCase
import com.example.domain.use_cases.children.paged.SearchForChildrenAddedByEmployeeByNameUseCase
import com.example.domain.use_cases.children.paged.SearchForChildrenByNameUseCase
import com.example.domain.use_cases.upload_child_documents.UploadChildDocumentsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val childDomainModule = module{
    singleOf(::AddChildUseCase)
    singleOf(::GetChildByIdUseCase)
    singleOf(::GetChildrenByGuardianIdUseCase)

    //flows
    singleOf(::SearchForChildrenByNameUseCase)
    singleOf(::SearchForChildrenAddedByEmployeeByNameUseCase)

    singleOf(::UploadChildDocumentsUseCase)
}