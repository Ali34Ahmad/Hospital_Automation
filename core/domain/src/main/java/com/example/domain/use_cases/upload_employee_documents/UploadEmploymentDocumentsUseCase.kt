package com.example.domain.use_cases.upload_employee_documents

import android.net.Uri
import com.example.domain.repositories.UploadEmploymentDocumentsRepository
import com.example.model.enums.Role
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

class UploadEmploymentDocumentsUseCase(
    private val uploadEmploymentDocumentsRepository:UploadEmploymentDocumentsRepository
) {
    suspend operator fun invoke(uri: Uri,role: Role): Flow<ProgressUpdate> {
        return uploadEmploymentDocumentsRepository.uploadFile(uri = uri,role=role)
    }
}