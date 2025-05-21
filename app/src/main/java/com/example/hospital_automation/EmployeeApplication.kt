package com.example.hospital_automation

import android.app.Application
import com.example.add_child_screen.di.addChildModule
import com.example.add_residential_address.addResidentialAddressModule
import com.example.child_profile.di.childProfileModule
import com.example.children_search.di.childrenSearchModule
import com.example.data.di.dataModule
import com.example.datastore.di.dataStoreModule
import com.example.email_verification.emailVerificationModule
import com.example.employee_profile.employeeProfileModule
import com.example.enter_email.enterEmailModule
import com.example.guardians_search.di.guardiansSearchModule
import com.example.home.homeModule
import com.example.hospital_automation.app_logic.appModule
import com.example.login.loginModule
import com.example.network.di.networkModule
import com.example.reset_password.resetPasswordModule
import com.example.signup.signUpModule
import com.example.upload_employee_documents.uploadEmployeeDocumentsModule
import com.example.upload_employee_profile_image.uploadEmployeeProfileImageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmployeeApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EmployeeApplication)
            modules(
                signUpModule,
                emailVerificationModule,
                loginModule,
                uploadEmployeeDocumentsModule,
                addResidentialAddressModule,
                uploadEmployeeProfileImageModule,
                enterEmailModule,
                resetPasswordModule,
                //
                homeModule,
                employeeProfileModule,
                networkModule,
                dataStoreModule,
                //
                appModule,
                dataModule,
                guardiansSearchModule,
                childrenSearchModule,
                childProfileModule,
                addChildModule
            )
        }
    }
}