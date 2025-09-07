package com.example.network.remote.appointment_type

import com.example.network.model.dto.doctor.appointment.AppointmentTypeSummaryDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AppointmentTypeApiService {

    suspend fun createAppointmentType(
        token: String,
        appointmentType: AppointmentTypeSummaryDto,
    ): Result<Unit, NetworkError>

    suspend fun updateAppointType(
        token: String,
        appointmentType: AppointmentTypeSummaryDto,
        id: Int
    ): Result<Unit, NetworkError>

    suspend fun deleteAppointmentType(
        token: String,
        id: Int
    ): Result<Unit, NetworkError>

}