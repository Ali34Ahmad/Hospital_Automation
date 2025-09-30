package com.example.network.remote.admin_profile

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.AdminProfileResponseDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val ADMIN_PROFILE_API_TAG = "AdminProfileApi"

class AdminProfileApiServiceImpl(
    private val client: HttpClient,
) : AdminProfileApiService {
    override suspend fun getAdminInfoById(
        adminId: Int?,
        token: String,
        role: RoleDto,
    ): Result<AdminProfileResponseDto, NetworkError> =
        doApiCall<AdminProfileResponseDto>(
            tag = ADMIN_PROFILE_API_TAG
        ) {
            Log.d(ADMIN_PROFILE_API_TAG, "getAdminInfoById")

            val routeSuffix = if (adminId != null) "/$adminId" else ""
            client.get("${ApiRoutes.getAdminProfileEndPoint(role,adminId==null)}$routeSuffix") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }
}