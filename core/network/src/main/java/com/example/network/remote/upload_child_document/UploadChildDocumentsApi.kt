package com.example.network.remote.upload_child_document

import android.net.Uri
import com.example.network.model.response.ProgressUpdateDto
import kotlinx.coroutines.flow.Flow

interface UploadChildDocumentsApi {
    fun uploadFile(token: String, uri: Uri, id: Int): Flow<ProgressUpdateDto>
}