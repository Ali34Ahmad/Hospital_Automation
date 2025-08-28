package com.example.network.remote.doctor.profile

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.DoctorProfileResponseDto
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface DoctorProfileApiService {
    suspend fun getDoctorInfo(token: String,roleDto: RoleDto,id:Int?): Result<DoctorProfileResponseDto, rootError>
}