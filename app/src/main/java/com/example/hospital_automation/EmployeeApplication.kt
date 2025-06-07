package com.example.hospital_automation

import android.app.Application
import com.example.children_search.di.childrenSearchModule
import com.example.data.di.employeeDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmployeeApplication: Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@EmployeeApplication)
            modules(
                employeeDataModule,
                childrenSearchModule
            )
        }
    }
}