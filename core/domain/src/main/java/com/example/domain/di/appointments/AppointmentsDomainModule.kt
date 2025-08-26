package com.example.domain.di.appointments

import com.example.domain.use_cases.doctor.appointment.AddDiagnosisUseCase
import com.example.domain.use_cases.doctor.appointment.GetAppointmentDetailsUseCase
import com.example.domain.use_cases.doctor.appointment.GetAppointmentsFlowUseCase
import com.example.domain.use_cases.doctor.appointment.UpdateAppointmentStateToMissedUseCase
import com.example.domain.use_cases.doctor.appointment.UpdateAppointmentStateToPassedUseCase
import com.example.domain.use_cases.doctor.profile.GetCurrentDoctorProfileUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val appointmentUseCasesModule = module {
    singleOf(::GetAppointmentsFlowUseCase)
    singleOf(::GetAppointmentDetailsUseCase)
    singleOf(::UpdateAppointmentStateToPassedUseCase)
    singleOf(::UpdateAppointmentStateToMissedUseCase)
    singleOf(::AddDiagnosisUseCase)
}