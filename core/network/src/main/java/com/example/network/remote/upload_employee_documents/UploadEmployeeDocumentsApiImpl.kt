package com.example.network.remote.upload_employee_documents

import android.net.Uri
import com.example.network.model.response.ProgressUpdate
import com.example.network.utility.ApiRoutes
import com.example.network.utility.file.FileReader
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class UploadEmployeeDocumentsApiImpl(
    private val client: HttpClient,
    private val fileReader: FileReader,
):UploadEmployeeDocumentsApi {
    override fun uploadFile(uri: Uri): Flow<ProgressUpdate> = channelFlow {
        val info = fileReader.uriToFileInfo(uri)
        client.submitFormWithBinaryData(
            url = ApiRoutes.UPLOAD_EMPLOYEE_DOCUMENTS,
            formData = formData {
                append("description", "Test")
                append("file", info.bytes, Headers.build {
                    append(HttpHeaders.ContentType, info.mimeType)
                    append(HttpHeaders.ContentDisposition, "filename: ${info.name}")
                })
            }
        ) {
            onUpload { bytesSent, totalBytes ->
                if ((totalBytes ?: 0) > 0L) {
                    send(ProgressUpdate(bytesSent, totalBytes ?: 0))
                }
            }
        }
    }
}

