package com.example.network.remote.admin_profile

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.response.AdminProfileResponseDto
import com.example.network.model.response.NetworkMessage
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first

class AdminProfileApiServiceImpl(
    private val client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
) : AdminProfileApiService {
    override suspend fun getAdminInfoById(adminId: Int): Result<AdminProfileResponseDto, rootError> = try {
        val response = client.get("${ApiRoutes.ADMIN_PROFILE_BY_ID}/$adminId") {
            contentType(ContentType.Application.Json)
            val token = userPreferencesRepository.userPreferencesFlow.first().token
            if (token == null) return@get
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
                Log.e("AdminId:",adminId.toString())
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("AdminProfileApi:Exception", e.message?:"Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }
}