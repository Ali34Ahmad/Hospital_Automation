package com.example.network.remote.upload_profile_image

import android.net.Uri
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.ProgressUpdateDto
import com.example.network.remote.upload_image.UploadImageApi
import com.example.network.utility.ApiRoutes
import kotlinx.coroutines.flow.Flow

class UploadEmployeeProfileImageApiImpl(
    private val uploadImageApi: UploadImageApi,
) : UploadEmployeeProfileImageApi {

    override fun uploadImage(token: String, uri: Uri, role: RoleDto): Flow<ProgressUpdateDto> =
        uploadImageApi.uploadImage(
            token, uri, ApiRoutes.getUploadProfileImageEndPointFor(role = role),
            isEmployee = role == RoleDto.EMPLOYEE
        )
}
