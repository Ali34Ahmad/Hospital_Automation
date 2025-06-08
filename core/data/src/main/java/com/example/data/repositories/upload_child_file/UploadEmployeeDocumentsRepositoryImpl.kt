package com.example.data.repositories.upload_child_file

import android.net.Uri
import com.example.data.mapper.file.toProgressUpdate
import com.example.domain.repositories.UploadChildDocumentsRepository
import com.example.model.file.ProgressUpdate
import com.example.network.remote.upload_child_document.UploadChildDocumentsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UploadChildDocumentsRepositoryImpl(
    private val uploadChildDocumentsApi:UploadChildDocumentsApi
):UploadChildDocumentsRepository {
    override fun uploadFile(uri: Uri,id: Int): Flow<ProgressUpdate> =
        uploadChildDocumentsApi.uploadFile(
            uri,
            id
        ).map {progressUpdateDto->
            progressUpdateDto.toProgressUpdate()
        }
}