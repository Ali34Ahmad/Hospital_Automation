package com.example.domain.use_cases.upload_child_documents

import android.net.Uri
import com.example.domain.repositories.UploadChildDocumentsRepository
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

class UploadChildDocumentsUseCase(
    private val uploadChildDocumentsRepository:UploadChildDocumentsRepository
) {
    suspend operator fun invoke(uri: Uri,id: Int): Flow<ProgressUpdate> {
        return uploadChildDocumentsRepository.uploadFile(uri = uri,id = id)
    }
}