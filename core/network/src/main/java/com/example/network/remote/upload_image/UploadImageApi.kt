package com.example.network.remote.upload_image

import android.net.Uri
import com.example.network.model.response.ProgressUpdateDto
import kotlinx.coroutines.flow.Flow

interface UploadImageApi {
    fun uploadImage(token:String,uri: Uri,endPoint: String,isEmployee: Boolean): Flow<ProgressUpdateDto>
}