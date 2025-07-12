package com.example.domain.repositories.file

import android.net.Uri
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

interface UploadChildDocumentsRepository {
    suspend fun uploadFile(uri: Uri,id: Int): Flow<ProgressUpdate>
}