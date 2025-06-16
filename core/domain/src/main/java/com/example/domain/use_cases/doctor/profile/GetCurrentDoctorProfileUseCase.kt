package com.example.domain.use_cases.doctor.profile

import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError

class GetCurrentDoctorProfileUseCase(

) {
    suspend operator fun invoke(): Result<DoctorProfileResponse, rootError>{
        return Result.Error(NetworkError.UNKNOWN)
    }
}