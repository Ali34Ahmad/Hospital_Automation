package com.example.admin_app

import android.app.Application
import com.example.admin_app.main.appModule
import com.example.clinic_details.di.clinicDetailsModule
import com.example.clinics_search.di.clinicsSearchModule
import com.example.data.di.adminDataModule
import com.example.doctor_schedule.di.doctorScheduleModule
import com.example.doctors.di.doctorsSearchModule
import com.example.employees_search.di.employeesSearchModule
import com.example.pharmacies_search.di.pharmaciesSearch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AdminApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AdminApplication)
            modules(
                appModule,
                adminDataModule,
                //features
                doctorsSearchModule,
                employeesSearchModule,
                clinicsSearchModule,
                pharmaciesSearch,
                doctorScheduleModule,
                clinicDetailsModule
            )
        }
    }
}