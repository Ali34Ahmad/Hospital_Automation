package com.example.domain.repositories.admin.doctor

import androidx.paging.PagingData
import com.example.model.admin.EmploymentStatistics
import com.example.model.doctor.DoctorData
import com.example.model.employee.EmployeeState
import kotlinx.coroutines.flow.Flow

interface AdminDoctorRepository {
    suspend fun getDoctors(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics)-> Unit
        ): Flow<PagingData<DoctorData>>

    suspend fun getDoctorsByClinic(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics)-> Unit,
        clinicId: Int,
    ): Flow<PagingData<DoctorData>>
}