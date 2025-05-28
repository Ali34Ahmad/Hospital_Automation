package com.example.hospital_automation

import android.app.Application
import com.example.add_child_screen.di.addChildModule
import com.example.child_profile.di.childProfileModule
import com.example.children.di.childrenModule
import com.example.children_search.di.childrenSearchModule
import com.example.data.di.dataModule
import com.example.datastore.di.dataStoreModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.guardians_search.di.guardiansSearchModule
import com.example.hospital_automation.app_logic.appModule
import com.example.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmployeeApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EmployeeApplication)
            //remove all modules that is not a feature module or data module.
            modules(
                networkModule,
                dataStoreModule,
                appModule,
                dataModule,
                guardiansSearchModule,
                childrenSearchModule,
                childProfileModule,
                addChildModule,
                guardianProfileModule,
                childrenModule
            )
        }
    }
}