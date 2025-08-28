package com.example.data.repositories.doctor

import com.example.data.mapper.doctor.toDoctorProfileResponse
import com.example.data.mapper.enums.toRoleDto
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.domain.repositories.profile.DoctorProfileRepository
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.doctor.profile.DoctorProfileApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class DoctorProfileRepositoryImpl(
    private val doctorProfileService: DoctorProfileApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val roleAppConfig: RoleAppConfig,
) : DoctorProfileRepository {
    override suspend fun getDoctorInfo(id: Int?): Result<DoctorProfileResponse, rootError> =
        userPreferencesRepository.executeWithValidToken { token ->
            doctorProfileService.getDoctorInfo(
                token = token,
                roleDto = roleAppConfig.role.toRoleDto(),
                id = id,
            )
                .map { doctorProfileResponseDto ->
                    doctorProfileResponseDto.toDoctorProfileResponse()
                }
        }
}