package com.example.network.remote.employee

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.employee.GetEmployeeProfileByIdResponseDto
import com.example.network.model.response.profile.EmployeeProfileResponseDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val EMPLOYEE_API_TAG = "EmployeeApi"

class EmployeeApiServiceImpl(
    private val client: HttpClient,
) : EmployeeApiService {
    override suspend fun getEmployeeInfo(
        token: String
    ): Result<EmployeeProfileResponseDto, NetworkError> =
        doApiCall<EmployeeProfileResponseDto>(
            tag = EMPLOYEE_API_TAG
        ) {
            Log.d(EMPLOYEE_API_TAG, "getEmployeeInfo")

            client.get(ApiRoutes.EMPLOYEE_PROFILE) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun getEmployeeInfoById(
        token: String,
        id: Int,
        roleDto: RoleDto
    ): Result<GetEmployeeProfileByIdResponseDto, NetworkError> =
        doApiCall<GetEmployeeProfileByIdResponseDto>(
            tag = EMPLOYEE_API_TAG
        ) {
            Log.d(EMPLOYEE_API_TAG, "getEmployeeInfoById")

            client.get("${ApiRoutes.getEmployeeProfileByIdEndPoint(roleDto)}/$id") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }
}