package com.example.data.repositories.upload_employee_image

import android.net.Uri
import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.file.toProgressUpdate
import com.example.domain.repositories.file.UploadEmployeeProfileImageRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.enums.Role
import com.example.model.file.ProgressUpdate
import com.example.network.remote.upload_profile_image.UploadEmployeeProfileImageApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UploadEmployeeProfileImageRepositoryImpl(
    private val uploadEmployeeProfileImageApi: UploadEmployeeProfileImageApi,
    private val userPreferencesRepository: UserPreferencesRepository,
) : UploadEmployeeProfileImageRepository {
    override suspend fun uploadImage(uri: Uri,role: Role): Flow<ProgressUpdate> =
        userPreferencesRepository.executeFlowWithValidToken { token ->
            uploadEmployeeProfileImageApi.uploadImage(
                token = token,
                uri = uri,
                role = role.toRoleDto()
            ).map { progressUpdateDto ->
                progressUpdateDto.toProgressUpdate()
            }
        }

}