package com.example.domain.repositories.file

import android.net.Uri
import com.example.model.enums.Role
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

interface UploadEmploymentDocumentsRepository {
    suspend fun uploadFile(uri: Uri,role: Role): Flow<ProgressUpdate>
}