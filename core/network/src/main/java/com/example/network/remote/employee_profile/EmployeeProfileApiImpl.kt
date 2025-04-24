package com.example.network.remote.employee_profile

import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.ProfileResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class EmployeeProfileApiImpl(
    private val client: HttpClient,
) : EmployeeProfileApi {
    override suspend fun getEmployeeInfo(): Resource<ProfileResponse> = try {
        val response = client.get(ApiRoutes.EMPLOYEE_PROFILES) {
            contentType(ContentType.Application.Json)
        }
        when (response.status) {
            HttpStatusCode.OK -> {
                val employeeProfile: ProfileResponse = response.body()
                Resource.Success(data = employeeProfile)
            }

            else -> {
                val errorMessage: NetworkMessage = response.body()
                Resource.Error(message = errorMessage.message)
            }
        }
    } catch (e: Exception) {
        Resource.Error(message = "Network error during getting employee profile: ${e.localizedMessage}")
    }
}