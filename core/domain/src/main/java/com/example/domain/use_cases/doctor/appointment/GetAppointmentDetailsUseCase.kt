package com.example.domain.use_cases.doctor.appointment

import com.example.domain.repositories.AppointmentsRepository

data class GetAppointmentDetailsUseCase(
    private val repo: AppointmentsRepository
){
    suspend operator fun invoke(appointmentId : Int) = repo
        .getAppointmentDetails(id = appointmentId)
}
