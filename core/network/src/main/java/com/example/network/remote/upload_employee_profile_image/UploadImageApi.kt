package com.example.network.remote.upload_employee_profile_image

import android.net.Uri
import com.example.network.model.response.ProgressUpdateDto
import kotlinx.coroutines.flow.Flow

interface UploadImageApi {
    fun uploadImage(uri: Uri,endPoint: String): Flow<ProgressUpdateDto>
}