package com.example.data.repositories.upload_employee_file

import android.net.Uri
import com.example.data.mapper.enums.toRoleDto
import com.example.data.mapper.file.toProgressUpdate
import com.example.domain.repositories.UploadEmploymentDocumentsRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.enums.Role
import com.example.model.file.ProgressUpdate
import com.example.network.remote.upload_employee_documents.UploadEmploymentDocumentsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UploadEmploymentDocumentsRepositoryImpl(
    private val uploadEmploymentDocumentsApi:UploadEmploymentDocumentsApi,
    private val userPreferencesRepository: UserPreferencesRepository
):UploadEmploymentDocumentsRepository {
    override suspend fun uploadFile(uri: Uri,role: Role): Flow<ProgressUpdate> =
        userPreferencesRepository.executeFlowWithValidToken{token->
            uploadEmploymentDocumentsApi.uploadFile(
                token=token,
                uri=uri,
                role = role.toRoleDto(),
            ).map { progressUpdateDto ->
                progressUpdateDto.toProgressUpdate()
            }
        }
}