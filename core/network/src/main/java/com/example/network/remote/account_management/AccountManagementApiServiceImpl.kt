package com.example.network.remote.account_management

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.DeactivateAccountRequestDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.profile.CheckPermissionResponseDto
import com.example.network.model.response.profile.DeactivateUserAccountResponseDto
import com.example.network.model.response.profile.ReactivateUserAccountResponseDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val ACCOUNT_MANAGEMENT_API_TAG = "AccountManagementApi"

class AccountManagementApiServiceImpl(
    private val client: HttpClient,
) : AccountManagementApiService {

    override suspend fun deactivateUserAccount(
        deactivateAccountRequestDto: DeactivateAccountRequestDto,
        token: String,
        role: RoleDto,
        userId: Int?,
    ): Result<DeactivateUserAccountResponseDto, NetworkError> =
        doApiCall<DeactivateUserAccountResponseDto>(
            tag = ACCOUNT_MANAGEMENT_API_TAG
        ) {
            Log.d(ACCOUNT_MANAGEMENT_API_TAG, "deactivateUserAccount")
            val routeSuffix = if (userId != null) "/$userId" else ""
            client.post("${ApiRoutes.getDeactivateAccountEndPointFor(role)}$routeSuffix") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(deactivateAccountRequestDto)
            }
        }

    override suspend fun reactivateUserAccount(
        token: String,
        role: RoleDto,
        userId: Int?,
    ): Result<ReactivateUserAccountResponseDto, NetworkError> =
        doApiCall<ReactivateUserAccountResponseDto>(
            tag = ACCOUNT_MANAGEMENT_API_TAG
        ) {
            Log.d(ACCOUNT_MANAGEMENT_API_TAG, "reactivateUserAccount")

            val routeSuffix = if (userId != null) "/$userId" else ""
            client.post("${ApiRoutes.getReactivateUserAccountEndPointFor(role)}$routeSuffix") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun checkEmployeePermission(
        token: String,
        role: RoleDto
    ): Result<CheckPermissionResponseDto, NetworkError> =
        doApiCall<CheckPermissionResponseDto>(
            tag = ACCOUNT_MANAGEMENT_API_TAG
        ) {
            Log.d(ACCOUNT_MANAGEMENT_API_TAG, "checkEmployeePermission")

            client.get(ApiRoutes.getCheckPermissionEndPointFor(role = role)) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun resignUser(
        token: String,
        userId: Int?
    ): Result<DeactivateUserAccountResponseDto, NetworkError> =
        doApiCall<DeactivateUserAccountResponseDto>(
            tag = ACCOUNT_MANAGEMENT_API_TAG
        ) {
            Log.d(ACCOUNT_MANAGEMENT_API_TAG, "resignUser")

            client.post("${ApiRoutes.Admin.RESIGN_USER}/$userId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun deactivatePharmacyAccount(
        deactivateAccountRequestDto: DeactivateAccountRequestDto,
        token: String,
        pharmacyId: Int?
    ): Result<DeactivateUserAccountResponseDto, NetworkError> =
        doApiCall<DeactivateUserAccountResponseDto>(
            tag = ACCOUNT_MANAGEMENT_API_TAG
        ) {
            Log.d(ACCOUNT_MANAGEMENT_API_TAG, "deactivatePharmacyAccount")

            client.post("${ApiRoutes.Admin.DEACTIVATE_PHARMACY}/$pharmacyId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(deactivateAccountRequestDto)
            }
        }

    override suspend fun reactivatePharmacyAccount(
        token: String,
        pharmacyId: Int?
    ): Result<ReactivateUserAccountResponseDto, NetworkError> =
        doApiCall<ReactivateUserAccountResponseDto>(
            tag = ACCOUNT_MANAGEMENT_API_TAG
        ) {
            Log.d(ACCOUNT_MANAGEMENT_API_TAG, "reactivatePharmacyAccount")

            client.post("${ApiRoutes.Admin.REACTIVATE_PHARMACY}/$pharmacyId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }
}