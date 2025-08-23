package com.example.doctor_app

import android.app.Application
import com.example.add_new_vaccine.addNewVaccineModule
import com.example.add_residential_address.addResidentialAddressModule
import com.example.appointment_details.di.appointmentDetailsModule
import com.example.child_profile.di.childProfileModule
import com.example.clinic_details.di.clinicDetailsModule
import com.example.clinics_search.di.clinicsSearchModule
import com.example.data.di.doctorDataModule
import com.example.doctor_app.main.appModule
import com.example.doctor_profile.doctorProfileModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.doctor_signup.doctorSignUpModule
import com.example.email_verification.emailVerificationModule
import com.example.employment_history.employmentHistoryModule
import com.example.enter_email.enterEmailModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.login.loginModule
import com.example.medical_diagnosis.di.diagnosisModule
import com.example.medical_prescription_details.prescriptionDetailsModule
import com.example.prescriptions.medicalPrescriptionsModule
import com.example.medical_records.medicalRecordsModule
import com.example.medicine_details.di.medicineDetailsModule
import com.example.medicines_search.di.medicinesSearchModule
import com.example.pharmacies.di.pharmaciesModule
import com.example.pharmacy_details.pharmacyDetailsModule
import com.example.reset_password.resetPasswordModule
import com.example.upload_employee_documents.uploadEmploymentDocumentsModule
import com.example.upload_profile_image.uploadProfileImageModule
import com.example.vaccine_details_screen.vaccineDetailsModule
import com.example.vaccines.vaccinesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoctorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DoctorApplication)
            modules(
                doctorDataModule,
                addResidentialAddressModule,
                doctorProfileModule,
                addNewVaccineModule,
                appModule,
                doctorSignUpModule,
                emailVerificationModule,
                loginModule,
                resetPasswordModule,
                enterEmailModule,
                uploadProfileImageModule,
                uploadEmploymentDocumentsModule,
                addNewVaccineModule,
                addResidentialAddressModule,
                employmentHistoryModule,
                vaccineDetailsModule,
                //Ali Mansoura feature modules
                clinicsSearchModule,
                clinicDetailsModule,
                doctorScheduleModule,
                appointmentDetailsModule,
                diagnosisModule,
                medicineDetailsModule,
                medicinesSearchModule,
                pharmaciesModule,
                medicalPrescriptionsModule,
                vaccinesModule,
                pharmacyDetailsModule,
                prescriptionDetailsModule,
                medicalRecordsModule,
                childProfileModule,
                guardianProfileModule,
            )
        }
    }
}