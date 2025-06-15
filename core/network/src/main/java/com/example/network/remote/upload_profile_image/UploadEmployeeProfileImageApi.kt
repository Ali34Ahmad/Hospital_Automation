package com.example.network.remote.upload_profile_image

import android.net.Uri
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.ProgressUpdateDto
import kotlinx.coroutines.flow.Flow

interface UploadEmployeeProfileImageApi {
    fun uploadImage(token:String,uri: Uri,role: RoleDto): Flow<ProgressUpdateDto>
}