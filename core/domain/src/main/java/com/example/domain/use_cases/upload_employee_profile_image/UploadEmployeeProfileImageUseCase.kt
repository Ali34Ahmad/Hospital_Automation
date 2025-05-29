package com.example.domain.use_cases.upload_employee_profile_image

import android.net.Uri
import com.example.domain.repositories.UploadEmployeeDocumentsRepository
import com.example.domain.repositories.UploadEmployeeProfileImageRepository
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

class UploadEmployeeProfileImageUseCase(
    private val uploadEmployeeProfileImageRepository: UploadEmployeeProfileImageRepository
) {
    operator fun invoke(uri: Uri): Flow<ProgressUpdate> {
        return uploadEmployeeProfileImageRepository.uploadImage(uri = uri)
    }
}