package com.example.data.di

import com.example.data.repositories.child.ChildRepositoryImp
import com.example.data.repositories.user.UserRepositoryImp
import com.example.datastore.di.dataStoreModule
import com.example.domain.repositories.ChildRepository
import com.example.domain.repositories.UserRepository
import com.example.network.di.networkModule
import org.koin.dsl.module

val dataModule = module {
    includes(networkModule, dataStoreModule)
    single<UserRepository> { UserRepositoryImp(get(),get()) }
    single<ChildRepository> { ChildRepositoryImp(get(),get()) }
}