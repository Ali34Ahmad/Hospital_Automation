package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import kotlinx.coroutines.flow.Flow

interface DoctorRepository {
    suspend fun getAppointments(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        queryFilter: String?,
        dateFilter: String?
    ): Flow<PagingData<AppointmentData>>
}