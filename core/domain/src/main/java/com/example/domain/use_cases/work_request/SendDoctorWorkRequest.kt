package com.example.domain.use_cases.work_request

import com.example.domain.repositories.work_request.WorkRequestRepository
import com.example.model.work_request.RequestType

data class SendDoctorWorkRequestUseCase(
    private val repository: WorkRequestRepository
){
    suspend operator fun invoke(
        clinicId: Int,
    ) = repository.sendWorkRequest(
        requestType = RequestType.DOCTOR,
        clinicId = clinicId
    )
}
