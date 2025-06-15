package com.example.doctor_app

import android.app.Application
import com.example.data.di.dataModule
import com.example.doctor_schedule.di.doctorScheduleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DoctorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //add koin here
        startKoin {
            androidContext(this@DoctorApplication)
            modules(
                dataModule,
                doctorScheduleModule,
            )
        }
    }
}