package com.example.network.remote.doctor.profile

import android.util.Log
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.profile.EmployeeProfileResponseDto
import com.example.network.model.response.employee.GetEmployeeProfileByIdResponseDto
import com.example.network.model.response.profile.DoctorProfileResponseDto
import com.example.network.remote.employee_profile.EmployeeProfileApiService
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

class DoctorProfileApiServiceImpl(
    private val client: HttpClient,
) : DoctorProfileApiService {
    override suspend fun getDoctorInfo(token: String): Result<DoctorProfileResponseDto, rootError> = try {
        val response = client.get(ApiRoutes.Doctor.PROFILE) {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            Log.v("DoctorProfileApi:", token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val employeeProfile: DoctorProfileResponseDto = response.body()
                Log.v("DoctorProfileApi:In Range 2xx", "getDoctorInfo: $employeeProfile")
                Result.Success(data = employeeProfile)
            }

            else -> {
                val errorMessage: NetworkMessage = response.body()
                Log.e("DoctorProfileApi:Out of Range 2xx", errorMessage.message)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("DoctorProfileApi:Exception", e.message ?: "Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }
}