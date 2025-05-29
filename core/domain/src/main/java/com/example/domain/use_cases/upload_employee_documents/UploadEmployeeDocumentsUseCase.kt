package com.example.domain.use_cases.upload_employee_documents

import android.net.Uri
import com.example.domain.repositories.UploadEmployeeDocumentsRepository
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

class UploadEmployeeDocumentsUseCase(
    private val uploadEmployeeDocumentsRepository:UploadEmployeeDocumentsRepository
) {
    operator fun invoke(uri: Uri): Flow<ProgressUpdate> {
        return uploadEmployeeDocumentsRepository.uploadFile(uri = uri)
    }
}