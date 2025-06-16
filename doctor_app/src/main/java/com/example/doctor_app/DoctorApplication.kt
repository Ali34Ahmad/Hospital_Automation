package com.example.doctor_app

import android.app.Application
import com.example.add_residential_address.addResidentialAddressModule
import com.example.data.di.doctorDataModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.doctor_signup.doctorSignUpModule
import com.example.email_verification.emailVerificationModule
import com.example.enter_email.enterEmailModule
import com.example.login.loginModule
import com.example.reset_password.resetPasswordModule
import com.example.upload_employee_documents.uploadEmploymentDocumentsModule
import com.example.upload_employee_profile_image.uploadEmployeeProfileImageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoctorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //add koin here
        startKoin {
            androidContext(this@DoctorApplication)
            modules(
                doctorDataModule,
                doctorScheduleModule,

                doctorSignUpModule,
                emailVerificationModule,
                uploadEmploymentDocumentsModule,
                addResidentialAddressModule,
                uploadEmployeeProfileImageModule,
                loginModule,
                enterEmailModule,
                resetPasswordModule,
            )
        }
    }
}