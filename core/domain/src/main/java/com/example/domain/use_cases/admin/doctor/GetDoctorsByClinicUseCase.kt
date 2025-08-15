package com.example.domain.use_cases.admin.doctor

import com.example.domain.repositories.admin.doctor.AdminDoctorRepository
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeState

class GetDoctorsByClinicUseCase(
    private val repository: AdminDoctorRepository
){
    suspend operator fun invoke(
        query: String,
        status: EmployeeState,
        onStatisticsUpdated: (EmploymentStatistics)-> Unit,
        clinicId: Int
    ) = repository.getDoctorsByClinic(
        query = query,
        status = status,
        onStatisticsUpdated = onStatisticsUpdated,
        clinicId = clinicId
    )
}