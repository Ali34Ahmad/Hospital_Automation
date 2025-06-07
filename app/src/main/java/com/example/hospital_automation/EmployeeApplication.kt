package com.example.hospital_automation

import android.app.Application
import com.example.add_child_screen.di.addChildModule
import com.example.child_profile.di.childProfileModule
import com.example.children.di.childrenModule
import com.example.children_search.di.childrenSearchModule
import com.example.data.di.employeeDataModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.guardians.di.guardiansModule
import com.example.guardians_search.di.guardiansSearchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmployeeApplication: Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@EmployeeApplication)
            modules(
                employeeDataModule,
                //guardians
                guardiansSearchModule,
                guardianProfileModule,
                guardiansModule,
                //children
                childrenModule,
                addChildModule,
                childProfileModule,
                childrenSearchModule
            )
        }
    }
}