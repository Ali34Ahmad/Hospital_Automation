package com.example.network.remote.upload_employee_documents

import android.net.Uri
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.ProgressUpdateDto
import kotlinx.coroutines.flow.Flow

interface UploadEmploymentDocumentsApi {
    fun uploadFile(token:String,uri: Uri,role: RoleDto): Flow<ProgressUpdateDto>
}