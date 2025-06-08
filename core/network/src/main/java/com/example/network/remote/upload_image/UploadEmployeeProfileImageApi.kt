package com.example.network.remote.upload_image

import android.net.Uri
import com.example.network.model.response.ProgressUpdateDto
import kotlinx.coroutines.flow.Flow

interface UploadEmployeeProfileImageApi {
    fun uploadImage(uri: Uri): Flow<ProgressUpdateDto>
}