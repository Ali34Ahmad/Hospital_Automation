package com.example.network.remote.upload_image

import android.net.Uri
import android.system.Os.close
import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.response.ProgressUpdateDto
import com.example.network.remote.upload_employee_profile_image.UploadImageApi
import com.example.network.utility.ApiRoutes
import com.example.network.utility.file.FileReader
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import java.io.File


class UploadEmployeeProfileImageApiImpl(
    private val uploadImageApi: UploadImageApi,
) : UploadEmployeeProfileImageApi {

    override fun uploadImage(uri: Uri): Flow<ProgressUpdateDto> =
        uploadImageApi.uploadImage(uri,ApiRoutes.UPLOAD_EMPLOYEE_PROFILE_IMAGE)
}
