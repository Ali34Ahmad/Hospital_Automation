package com.example.network.remote.upload_employee_documents

import android.net.Uri
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.ProgressUpdateDto
import com.example.network.remote.upload_file.UploadFileApiService
import com.example.network.utility.ApiRoutes
import kotlinx.coroutines.flow.Flow

class UploadEmploymentDocumentsApiImpl(
    private val uploadFileApi: UploadFileApiService,
) : UploadEmploymentDocumentsApi {
    override fun uploadFile(token: String, uri: Uri, role: RoleDto): Flow<ProgressUpdateDto> =
        uploadFileApi.uploadFile(
            token = token,
            uri = uri,
            endPoint = ApiRoutes.getUploadEmploymentDocumentsEndPointFor(role)
        )
}
