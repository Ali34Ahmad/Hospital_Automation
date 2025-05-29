package com.example.data.repositories.file

import android.net.Uri
import com.example.data.mapper.file.toProgressUpdate
import com.example.domain.repositories.UploadEmployeeDocumentsRepository
import com.example.model.file.ProgressUpdate
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UploadEmployeeDocumentsRepositoryImpl(
    private val uploadEmployeeDocumentsApi:UploadEmployeeDocumentsApi
):UploadEmployeeDocumentsRepository {
    override fun uploadFile(uri: Uri): Flow<ProgressUpdate> =
        uploadEmployeeDocumentsApi.uploadFile(
            uri
        ).map {progressUpdateDto->
            progressUpdateDto.toProgressUpdate()
        }
}