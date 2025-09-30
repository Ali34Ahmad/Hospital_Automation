package com.example.hospital_automation

import android.app.Application
import com.example.add_child_screen.di.addChildModule
import com.example.add_residential_address.di.addResidentialAddressModule
import com.example.admin_profile.di.adminProfileModule
import com.example.child_profile.di.childProfileModule
import com.example.children.di.childrenModule
import com.example.children_search.di.childrenSearchModule
import com.example.data.di.dataModule
import com.example.email_verification.di.emailVerificationModule
import com.example.employee_profile.di.employeeProfileModule
import com.example.employment_history.di.employmentHistoryModule
import com.example.enter_email.di.enterEmailModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.guardians.di.guardiansModule
import com.example.guardians_search.di.guardiansSearchModule
import com.example.home.di.employeeHomeModule
import com.example.hospital_automation.main.appModule
import com.example.login.di.loginModule
import com.example.reset_password.di.resetPasswordModule
import com.example.signup.di.signUpModule
import com.example.upload_child_documents.di.uploadChildDocumentsModule
import com.example.upload_employee_documents.di.uploadEmploymentDocumentsModule
import com.example.upload_profile_image.di.uploadProfileImageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmployeeApplication: Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@EmployeeApplication)
            modules(
                appModule,
                dataModule,
                //guardians
                guardiansSearchModule,
                guardianProfileModule,
                guardiansModule,
                //children
                childrenModule,
                addChildModule,
                childProfileModule,
                childrenSearchModule,

                addResidentialAddressModule,
                adminProfileModule,
                emailVerificationModule,
                employeeProfileModule,
                employmentHistoryModule,
                enterEmailModule,
                employeeHomeModule,
                loginModule,
                resetPasswordModule,
                signUpModule,
                uploadEmploymentDocumentsModule,
                uploadChildDocumentsModule,
                uploadProfileImageModule,
            )
        }
    }
}