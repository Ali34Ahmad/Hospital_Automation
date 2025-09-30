package com.example.network.remote.doctor.profile

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.DoctorProfileResponseDto
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface DoctorApiService {
    suspend fun getDoctorInfo(
        token: String,
        roleDto: RoleDto,
        id: Int?
    ): Result<DoctorProfileResponseDto, NetworkError>
}