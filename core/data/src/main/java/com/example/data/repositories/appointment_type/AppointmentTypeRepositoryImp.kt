package com.example.data.repositories.appointment_type

import com.example.data.mapper.doctor.toAppointmentTypeSummaryDto
import com.example.domain.repositories.AppointmentTypeRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.doctor.appointment.AppointmentTypeSummaryData
import com.example.network.remote.appointment_type.AppointmentTypeApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

class AppointmentTypeRepositoryImp(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val apiService: AppointmentTypeApiService
): AppointmentTypeRepository {
    override suspend fun createAppointmentType(
        appointmentType: AppointmentTypeSummaryData
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork {
            apiService.createAppointmentType(
                token = it,
                appointmentType = appointmentType.toAppointmentTypeSummaryDto()
            )
        }

    override suspend fun updateAppointment(
        appointmentType: AppointmentTypeSummaryData,
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork {
            apiService.updateAppointType(
                token = it,
                appointmentType = appointmentType.toAppointmentTypeSummaryDto(),
                id = appointmentType.id
            )
        }

    override suspend fun deleteAppointment(
        id: Int
    ): Result<Unit, NetworkError> =
        userPreferencesRepository.executeWithValidTokenNetwork {
            apiService.deleteAppointmentType(
                token = it,
                id = id
            )
        }
}