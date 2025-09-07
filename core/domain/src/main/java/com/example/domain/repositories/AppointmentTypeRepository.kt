package com.example.domain.repositories

import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AppointmentTypeRepository {
    suspend fun createAppointmentType(
        appointmentType: AppointmentTypeSummaryData
    ): Result<Unit, NetworkError>

    suspend fun updateAppointment(
        appointmentType: AppointmentTypeSummaryData,
        id: Int,
    ): Result<Unit, NetworkError>

    suspend fun deleteAppointment(
        id: Int
    ): Result<Unit, NetworkError>
}