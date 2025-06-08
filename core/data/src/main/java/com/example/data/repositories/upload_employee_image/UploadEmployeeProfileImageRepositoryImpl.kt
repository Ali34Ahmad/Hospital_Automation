package com.example.data.repositories.upload_employee_image

import android.net.Uri
import com.example.data.mapper.file.toProgressUpdate
import com.example.domain.repositories.UploadEmployeeProfileImageRepository
import com.example.model.file.ProgressUpdate
import com.example.network.remote.upload_employee_profile_image.UploadImageApi
import com.example.network.remote.upload_image.UploadEmployeeProfileImageApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UploadEmployeeProfileImageRepositoryImpl(
    private val uploadEmployeeProfileImageApi: UploadEmployeeProfileImageApi
): UploadEmployeeProfileImageRepository {
    override fun uploadImage(uri: Uri): Flow<ProgressUpdate> =
        uploadEmployeeProfileImageApi.uploadImage(
            uri
        ).map { progressUpdateDto->
            progressUpdateDto.toProgressUpdate()
        }

}