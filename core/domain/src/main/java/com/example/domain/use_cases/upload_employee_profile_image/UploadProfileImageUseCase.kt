package com.example.domain.use_cases.upload_employee_profile_image

import android.net.Uri
import com.example.domain.repositories.UploadEmployeeProfileImageRepository
import com.example.model.enums.Role
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

class UploadProfileImageUseCase(
    private val uploadEmployeeProfileImageRepository: UploadEmployeeProfileImageRepository
) {
   suspend operator fun invoke(uri: Uri,role: Role): Flow<ProgressUpdate> {
        return uploadEmployeeProfileImageRepository.uploadImage(uri = uri, role = role)
    }
}