package com.example.domain.di.domainModules

import com.example.domain.di.children.childDomainModule
import com.example.domain.di.user.userDomainModule
import org.koin.dsl.module

val employeeDomainModule = module {
    includes(
        userDomainModule,
        childDomainModule
    )
}