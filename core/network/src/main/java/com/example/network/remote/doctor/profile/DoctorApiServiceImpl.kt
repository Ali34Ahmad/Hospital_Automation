package com.example.network.remote.doctor.profile

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.DoctorProfileResponseDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val DOCTOR_API_TAG = "DoctorApi"

class DoctorApiServiceImpl(
    private val client: HttpClient,
) : DoctorApiService {
    override suspend fun getDoctorInfo(
        token: String,
        roleDto: RoleDto,
        id: Int?
    ): Result<DoctorProfileResponseDto, NetworkError> =
        doApiCall<DoctorProfileResponseDto>(
            tag = DOCTOR_API_TAG
        ) {
            Log.d(DOCTOR_API_TAG, "getDoctorInfo")

            val routeSuffix = if (id != null) "/$id" else ""
            client.get("${ApiRoutes.getDoctorProfileEndPoint(role = roleDto, id == null)}$routeSuffix") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }
}