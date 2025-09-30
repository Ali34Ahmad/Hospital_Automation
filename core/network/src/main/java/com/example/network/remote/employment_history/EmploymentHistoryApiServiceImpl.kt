package com.example.network.remote.employment_history

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.profile.EmploymentHistoryResponseDto
import com.example.network.model.response.profile.PharmacyContractHistoryDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val EMPLOYMENT_HISTORY_API_TAG = "EmploymentHistoryApi"

class EmploymentHistoryApiServiceImpl(
    private val client: HttpClient,
) : EmploymentHistoryApiService {
    override suspend fun getEmploymentHistory(
        token: String,
        role: RoleDto,
        id: Int?
    ): Result<EmploymentHistoryResponseDto, NetworkError> =
        doApiCall<EmploymentHistoryResponseDto>(
            tag = EMPLOYMENT_HISTORY_API_TAG
        ) {
            Log.d(EMPLOYMENT_HISTORY_API_TAG, "getEmploymentHistory")

            val routeSuffix = if (id != null) "/$id" else ""
            client.get("${ApiRoutes.getEmploymentHistoryEndPointFor(role)}$routeSuffix") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun getPharmacyContractHistory(
        token: String,
        pharmacyId: Int
    ): Result<PharmacyContractHistoryDto, NetworkError> =
        doApiCall<PharmacyContractHistoryDto>(
            tag = EMPLOYMENT_HISTORY_API_TAG
        ) {
            Log.d(EMPLOYMENT_HISTORY_API_TAG, "getPharmacyContractHistory")

            client.get("${ApiRoutes.Admin.GET_PHARMACY_CONTRACT_HISTORY}/$pharmacyId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                Log.d("PharmacyContractHistory", url.toString())
            }
        }
}