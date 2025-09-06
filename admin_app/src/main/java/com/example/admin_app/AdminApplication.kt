package com.example.admin_app

import android.app.Application
import com.example.add_residential_address.addResidentialAddressModule
import com.example.admin_app.main.appModule
import com.example.admin_profile.adminProfileModule
import com.example.appointment_details.di.appointmentDetailsModule
import com.example.child_profile.di.childProfileModule
import com.example.children.di.childrenModule
import com.example.children_search.di.childrenSearchModule
import com.example.clinic_details.di.clinicDetailsModule
import com.example.clinics_search.di.clinicsSearchModule
import com.example.data.di.adminDataModule
import com.example.data.di.dataModule
import com.example.doctor_profile.doctorProfileModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.doctors.di.doctorsSearchModule
import com.example.email_verification.emailVerificationModule
import com.example.employee_profile.employeeProfileModule
import com.example.employees_search.di.employeesSearchModule
import com.example.employment_history.employmentHistoryModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.medicine_details.di.medicineDetailsModule
import com.example.employment_requests.employmentRequestsModule
import com.example.enter_email.enterEmailModule
import com.example.generic_vaccination_table.genericVaccinationTableModule
import com.example.guardians.di.guardiansModule
import com.example.login.loginModule
import com.example.pharmacies_search.di.pharmaciesSearch
import com.example.pharmacy_details.pharmacyDetailsModule
import com.example.pharmacy_medicines.di.pharmacyMedicinesModule
import com.example.prescription_details.prescriptionDetailsModule
import com.example.reset_password.resetPasswordModule
import com.example.signup.signUpModule
import com.example.upload_profile_image.uploadProfileImageModule
import com.example.vaccine_details_screen.vaccineDetailsModule
import com.example.guardians.di.guardiansModule
import com.example.vaccines.vaccinesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AdminApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AdminApplication)
            modules(
                appModule,
                dataModule,
                //features
                guardiansModule,
                doctorsSearchModule,
                employeesSearchModule,
                clinicsSearchModule,
                pharmaciesSearch,
                doctorScheduleModule,
                clinicDetailsModule,
                signUpModule,
                emailVerificationModule,
                loginModule,
                addResidentialAddressModule,
                enterEmailModule,
                resetPasswordModule,
                uploadProfileImageModule,
                employmentRequestsModule,
                vaccinesModule,
                vaccineDetailsModule,
                guardianProfileModule,
                childProfileModule,
                appointmentDetailsModule,
                medicineDetailsModule,
                childrenSearchModule,
                childrenModule,
                pharmacyMedicinesModule,
                doctorProfileModule,
                employeeProfileModule,
                pharmacyDetailsModule,
                employmentHistoryModule,
                prescriptionDetailsModule,
                genericVaccinationTableModule,
                adminProfileModule,
            )
        }
    }
}