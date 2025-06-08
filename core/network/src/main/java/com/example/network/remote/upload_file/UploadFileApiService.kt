package com.example.network.remote.upload_file

import android.net.Uri
import com.example.network.model.response.ProgressUpdateDto
import kotlinx.coroutines.flow.Flow

interface UploadFileApiService {
    fun uploadFile(uri: Uri, endPoint: String): Flow<ProgressUpdateDto>
}