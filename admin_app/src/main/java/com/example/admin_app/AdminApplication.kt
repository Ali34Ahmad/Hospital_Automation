package com.example.admin_app

import android.app.Application
import com.example.add_residential_address.di.addResidentialAddressModule
import com.example.admin_app.main.appModule
import com.example.admin_profile.di.adminProfileModule
import com.example.appointment_details.di.appointmentDetailsModule
import com.example.child_profile.di.childProfileModule
import com.example.child_vaccination_table.di.childVaccinationTableModule
import com.example.children.di.childrenModule
import com.example.children_search.di.childrenSearchModule
import com.example.clinic_details.di.clinicDetailsModule
import com.example.clinics_search.di.clinicsSearchModule
import com.example.data.di.dataModule
import com.example.doctor_profile.di.doctorProfileModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.doctors.di.doctorsSearchModule
import com.example.email_verification.di.emailVerificationModule
import com.example.employee_profile.di.employeeProfileModule
import com.example.employees_search.di.employeesSearchModule
import com.example.employment_history.di.employmentHistoryModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.medicine_details.di.medicineDetailsModule
import com.example.employment_requests.di.employmentRequestsModule
import com.example.enter_email.di.enterEmailModule
import com.example.generic_vaccination_table.di.genericVaccinationTableModule
import com.example.guardians.di.guardiansModule
import com.example.login.di.loginModule
import com.example.medical_records.di.medicalRecordsModule
import com.example.pharmacies_search.di.pharmaciesSearch
import com.example.pharmacy_details.di.pharmacyDetailsModule
import com.example.pharmacy_medicines.di.pharmacyMedicinesModule
import com.example.prescription_details.di.prescriptionDetailsModule
import com.example.reset_password.di.resetPasswordModule
import com.example.signup.di.signUpModule
import com.example.upload_profile_image.di.uploadProfileImageModule
import com.example.vaccine_details_screen.di.vaccineDetailsModule
import com.example.prescriptions.di.prescriptionsModule
import com.example.vaccines.di.vaccinesModule
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
                prescriptionsModule,
                medicalRecordsModule,
                childVaccinationTableModule,
            )
        }
    }
}