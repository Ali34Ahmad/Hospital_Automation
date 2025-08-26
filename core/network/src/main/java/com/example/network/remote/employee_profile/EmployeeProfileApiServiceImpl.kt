package com.example.network.remote.employee_profile

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.profile.EmployeeProfileResponseDto
import com.example.network.model.response.employee.GetEmployeeProfileByIdResponseDto
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

class EmployeeProfileApiServiceImpl(
    private val client: HttpClient,
) : EmployeeProfileApiService {
    override suspend fun getEmployeeInfo(token: String): Result<EmployeeProfileResponseDto, rootError> = try {
        val response = client.get(ApiRoutes.EMPLOYEE_PROFILE) {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val employeeProfile: EmployeeProfileResponseDto = response.body()
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
        Log.v("EmployeeProfileApi:Exception", e.message ?: "Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }

    override suspend fun getEmployeeInfoById(token: String,id: Int,roleDto: RoleDto): Result<GetEmployeeProfileByIdResponseDto, rootError> =
        try {
            val response = client.get("${ApiRoutes.getEmployeeProfileByIdEndPoint(roleDto)}/$id") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val employeeProfile: GetEmployeeProfileByIdResponseDto = response.body()
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
            Log.v("EmployeeProfileApi:Exception", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }
}