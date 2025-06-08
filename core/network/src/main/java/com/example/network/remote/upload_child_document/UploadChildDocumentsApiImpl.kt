package com.example.network.remote.upload_child_document

import android.net.Uri
import com.example.network.model.response.ProgressUpdateDto
import com.example.network.remote.upload_file.UploadFileApiService
import com.example.network.utility.ApiRoutes
import kotlinx.coroutines.flow.Flow

class UploadChildDocumentsApiImpl(
    private val uploadFileApi: UploadFileApiService,
) : UploadChildDocumentsApi {
    override fun uploadFile(uri: Uri, id: Int): Flow<ProgressUpdateDto> =
        uploadFileApi.uploadFile(uri = uri, endPoint = "${ApiRoutes.UPLOAD_CHILD_CERTIFICATE}/$id")
}