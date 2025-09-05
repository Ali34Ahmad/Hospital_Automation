package com.example.network.remote.admin_profile

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.AdminProfileResponseDto
import com.example.network.model.response.NetworkMessage
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AdminProfileApiServiceImpl(
    private val client: HttpClient,
) : AdminProfileApiService {
    override suspend fun getAdminInfoById(
        adminId: Int?,
        token: String,
        role: RoleDto,
    ): Result<AdminProfileResponseDto, rootError> = try {
        val routeSuffix = if (adminId != null) "/$adminId" else ""
        val response = client.get("${ApiRoutes.getAdminProfileEndPoint(role)}$routeSuffix") {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val employeeProfile: AdminProfileResponseDto = response.body()
                Log.v("AdminProfileApi:In Range 2xx", "getAdminInfo: $employeeProfile")
                Result.Success(data = employeeProfile)
            }

            else -> {
                val errorMessage: NetworkMessage = response.body()
                Log.e("AdminProfileApi:Out of Range 2xx", errorMessage.message)
                Log.e("AdminId:", adminId.toString())
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("AdminProfileApi:Exception", e.message ?: "Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }
}