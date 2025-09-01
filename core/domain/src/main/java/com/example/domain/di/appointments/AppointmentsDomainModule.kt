package com.example.domain.di.appointments

import com.example.domain.use_cases.appointment.AddDiagnosisUseCase
import com.example.domain.use_cases.appointment.GetAppointmentDetailsUseCase
import com.example.domain.use_cases.appointment.GetChildAppointmentsUseCase
import com.example.domain.use_cases.appointment.GetDoctorAppointmentsUseCase
import com.example.domain.use_cases.appointment.GetUserAppointmentsUseCase
import com.example.domain.use_cases.appointment.UpdateAppointmentStateToMissedUseCase
import com.example.domain.use_cases.appointment.UpdateAppointmentStateToPassedUseCase
import com.example.domain.use_cases.doctor.profile.GetCurrentDoctorProfileUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val appointmentUseCasesModule = module {
    singleOf(::GetDoctorAppointmentsUseCase)
    singleOf(::GetAppointmentDetailsUseCase)
    singleOf(::UpdateAppointmentStateToPassedUseCase)
    singleOf(::UpdateAppointmentStateToMissedUseCase)
    singleOf(::AddDiagnosisUseCase)
    singleOf(::GetCurrentDoctorProfileUseCase)
    singleOf(::GetUserAppointmentsUseCase)
    singleOf(::GetChildAppointmentsUseCase)
}