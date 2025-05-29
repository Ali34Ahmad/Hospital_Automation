package com.example.domain.repositories

import android.net.Uri
import com.example.model.file.ProgressUpdate
import kotlinx.coroutines.flow.Flow

interface UploadEmployeeProfileImageRepository {
    fun uploadImage(uri: Uri): Flow<ProgressUpdate>
}