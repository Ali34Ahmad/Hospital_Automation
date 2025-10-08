package com.example.doctor_app

import android.app.Application
import com.example.add_new_vaccine.di.addNewVaccineModule
import com.example.add_residential_address.di.addResidentialAddressModule
import com.example.admin_profile.di.adminProfileModule
import com.example.appointment_details.di.appointmentDetailsModule
import com.example.child_profile.di.childProfileModule
import com.example.clinic_details.di.clinicDetailsModule
import com.example.clinics_search.di.clinicsSearchModule
import com.example.data.di.dataModule
import com.example.doctor_app.main.appModule
import com.example.doctor_profile.di.doctorProfileModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.doctor_signup.di.doctorSignUpModule
import com.example.edit_doctor_profile.di.editDoctorProfileModule
import com.example.email_verification.di.emailVerificationModule
import com.example.employment_history.di.employmentHistoryModule
import com.example.enter_email.di.enterEmailModule
import com.example.generic_vaccination_table.di.genericVaccinationTableModule
import com.example.guardian_profile.di.guardianProfileModule
import com.example.login.di.loginModule
import com.example.medical_diagnosis.di.diagnosisModule
import com.example.prescription_details.di.prescriptionDetailsModule
import com.example.prescriptions.di.prescriptionsModule
import com.example.medical_records.di.medicalRecordsModule
import com.example.medicine_details.di.medicineDetailsModule
import com.example.medicines_search.di.medicinesSearchModule
import com.example.pharmacies.di.pharmaciesModule
import com.example.pharmacy_details.di.pharmacyDetailsModule
import com.example.reset_password.di.resetPasswordModule
import com.example.upload_employee_documents.di.uploadEmploymentDocumentsModule
import com.example.upload_profile_image.di.uploadProfileImageModule
import com.example.vaccine_details_screen.di.vaccineDetailsModule
import com.example.vaccines.di.vaccinesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoctorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DoctorApplication)
            modules(
                dataModule,
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
                prescriptionsModule,
                vaccinesModule,
                pharmacyDetailsModule,
                prescriptionDetailsModule,
                medicalRecordsModule,
                childProfileModule,
                guardianProfileModule,
                genericVaccinationTableModule,
                adminProfileModule,
                editDoctorProfileModule
            )
        }
    }
}