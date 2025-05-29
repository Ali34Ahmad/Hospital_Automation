package com.example.domain.repositories

import android.net.Uri
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

interface UploadEmployeeDocumentsRepository {
    fun uploadFile(uri: Uri): Flow<ProgressUpdate>
}