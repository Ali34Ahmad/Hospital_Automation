package com.example.doctor_app

import android.app.Application
import com.example.add_new_vaccine.addNewVaccineModule
import com.example.add_residential_address.addResidentialAddressModule
import com.example.appointment_details.di.appointmentDetailsModule
import com.example.clinic_details.di.clinicDetailsModule
import com.example.clinics_search.di.clinicsSearchModule
import com.example.data.di.doctorDataModule
import com.example.doctor_profile.doctorProfileModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.medical_diagnosis.di.diagnosisModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoctorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DoctorApplication)
            modules(
                doctorDataModule,
                doctorScheduleModule,
                appointmentDetailsModule,
                diagnosisModule,
                addResidentialAddressModule,
                doctorProfileModule,
                addNewVaccineModule,
                clinicsSearchModule,
                clinicDetailsModule
            )
        }
    }
}