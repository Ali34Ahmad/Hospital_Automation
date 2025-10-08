package com.example.domain.use_cases.doctor.profile

import com.example.domain.repositories.profile.DoctorProfileRepository
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.NetworkError
import com.example.utility.network.map

class GetCurrentDoctorProfileUseCase(
    private val doctorProfileRepository:DoctorProfileRepository
) {
    suspend operator fun invoke(id:Int?=null): Result<DoctorProfileResponse, NetworkError>{
        return doctorProfileRepository.getDoctorInfo(id)
    }
}