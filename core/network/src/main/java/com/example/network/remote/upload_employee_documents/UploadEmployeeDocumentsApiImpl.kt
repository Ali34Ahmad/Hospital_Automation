package com.example.network.remote.upload_employee_documents

import android.net.Uri
import com.example.network.model.response.ProgressUpdateDto
import com.example.network.remote.upload_file.UploadFileApiService
import com.example.network.utility.ApiRoutes
import kotlinx.coroutines.flow.Flow

class UploadEmployeeDocumentsApiImpl(
    private val uploadFileApi: UploadFileApiService,
) : UploadEmployeeDocumentsApi {
    override fun uploadFile(uri: Uri): Flow<ProgressUpdateDto> =
        uploadFileApi.uploadFile(uri = uri, endPoint = ApiRoutes.UPLOAD_EMPLOYEE_DOCUMENTS)
}
