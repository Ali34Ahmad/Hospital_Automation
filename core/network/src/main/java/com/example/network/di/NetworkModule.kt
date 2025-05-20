package com.example.network.di

import com.example.network.remote.add_residential_address.AddResidentialAddressApi
import com.example.network.remote.add_residential_address.AddResidentialAddressApiImpl
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.auth.AuthApiServiceImpl
import com.example.network.remote.child.ChildApiService
import com.example.network.remote.child.ChildApiServiceImpl
import com.example.network.remote.employee_profile.EmployeeProfileApi
import com.example.network.remote.employee_profile.EmployeeProfileApiImpl
import com.example.network.remote.user.UserApiService
import com.example.network.remote.user.UserApiServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val networkModule = module{
    //ktor client
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout){
                requestTimeoutMillis = 100 * 1000
                connectTimeoutMillis = 100 * 1000
            }
        }
    }

    //child api service
    single<ChildApiService> { ChildApiServiceImpl(get()) }

    //user api service
    single<UserApiService>{ UserApiServiceImpl(get()) }

    single<AddResidentialAddressApi>{ AddResidentialAddressApiImpl(get()) }
    single<AuthApiService>{ AuthApiServiceImpl(get()) }
    single<EmployeeProfileApi>{ EmployeeProfileApiImpl(get()) }
}
