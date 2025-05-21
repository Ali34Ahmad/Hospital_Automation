package com.example.network.remote.employee_profile

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.EmployeeProfileResponse
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
import kotlinx.coroutines.flow.first

class EmployeeProfileApiServiceImpl(
    private val client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
) : EmployeeProfileApiService {
    override suspend fun getEmployeeInfo(): Result<EmployeeProfileResponse, rootError> = try {
        val response = client.get(ApiRoutes.EMPLOYEE_PROFILES) {
            contentType(ContentType.Application.Json)
            val token = userPreferencesRepository.userPreferencesFlow.first().token
            if (token == null) return@get
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val employeeProfile: EmployeeProfileResponse = response.body()
                Log.v("EmployeeProfileApi:In Range 2xx", "getEmployeeInfo: $employeeProfile")
                Result.Success(data = employeeProfile)
            }

            else -> {
                val errorMessage: NetworkMessage = response.body()
                Log.v("EmployeeProfileApi:Out of Range 2xx", errorMessage.message)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.v("EmployeeProfileApi:Exception", e.message?:"Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }
}