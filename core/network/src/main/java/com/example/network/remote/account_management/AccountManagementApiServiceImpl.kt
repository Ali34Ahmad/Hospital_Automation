package com.example.network.remote.account_management

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.DeactivateAccountRequestDto
import com.example.network.model.response.profile.CheckPermissionResponseDto
import com.example.network.model.response.profile.DeactivateUserAccountResponseDto
import com.example.network.model.response.NetworkMessage
import com.example.network.model.response.profile.ReactivateUserAccountResponseDto
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.rootError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import com.example.utility.network.Result
import io.ktor.client.request.get
import io.ktor.client.request.setBody

class AccountManagementApiServiceImpl(
    private val client: HttpClient,
) : AccountManagementApiService {

    override suspend fun deactivateUserAccount(
        deactivateAccountRequestDto: DeactivateAccountRequestDto,
        token: String,
        role: RoleDto,
        userId:Int?,
    ): Result<DeactivateUserAccountResponseDto, rootError> =
        try {
            val routeSuffix=if (userId!=null) "/$userId" else ""
            val response: HttpResponse =
                client.post("${ApiRoutes.getDeactivateAccountEndPointFor(role)}$routeSuffix") {
                    contentType(ContentType.Application.Json)
                    bearerAuth(token)
                    setBody(deactivateAccountRequestDto)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: DeactivateUserAccountResponseDto = response.body()
                    Log.v(
                        "DeactivateAccount: In Range 2xx",
                        "Successfully Deactivated ${responseBody.updatedData}"
                    )
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("DeactivateAccount: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("DeactivateAccount Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun reactivateUserAccount(
        token: String,
        role: RoleDto,
        userId:Int?,
    ): Result<ReactivateUserAccountResponseDto, rootError> =
        try {
            val routeSuffix=if (userId!=null) "/$userId" else ""
            val response: HttpResponse =
                client.post("${ApiRoutes.getReactivateUserAccountEndPointFor(role)}$routeSuffix") {
                    contentType(ContentType.Application.Json)
                    bearerAuth(token)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: ReactivateUserAccountResponseDto = response.body()
                    val responseBodyText: String = response.body()
                    Log.v(
                        "ReactivateAccount: In Range 2xx",
                        "Successfully Deactivated ${responseBody.updatedData}"
                    )
                    Log.v("ReactivateAccount: Body", responseBodyText)
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("ReactivateAccount: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("ReactivateAccount Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun checkEmployeePermission(
        token: String,
        role: RoleDto
    ): Result<CheckPermissionResponseDto, rootError> =
        try {
            val response: HttpResponse =
                client.get(ApiRoutes.getCheckPermissionEndPointFor(role = role)) {
                    contentType(ContentType.Application.Json)
                    bearerAuth(token)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: CheckPermissionResponseDto = response.body()
                    Log.v(
                        "Check Employee Permission: In Range 2xx",
                        "Successfully Checked ${responseBody.permissionGranted}"
                    )
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: String = response.body()
                    Log.e("Check Employee Permission: Out of Range 2xx", errorBody)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("Check Employee Permission Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

    override suspend fun resignUser(
        token: String,
        userId: Int?
    ): Result<DeactivateUserAccountResponseDto, rootError> =try {
        val response: HttpResponse =
            client.post("${ApiRoutes.Admin.RESIGN_USER}/$userId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        when (response.status.value) {
            in 200..299 -> {
                val responseBody: DeactivateUserAccountResponseDto = response.body()
                Log.v(
                    "ResignUser: In Range 2xx",
                    "Successfully Resigned ${responseBody.updatedData}"
                )
                Result.Success(data = responseBody)
            }

            else -> {
                val errorBody: String = response.body()
                Log.e("ResignUser: Out of Range 2xx", errorBody)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("ResignUser Exception:", e.message ?: "Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }

    override suspend fun deactivatePharmacyAccount(
        deactivateAccountRequestDto: DeactivateAccountRequestDto,
        token: String,
        pharmacyId: Int?
    ): Result<DeactivateUserAccountResponseDto, rootError> =try {
        val response: HttpResponse =
            client.post("${ApiRoutes.Admin.DEACTIVATE_PHARMACY}/$pharmacyId") {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(deactivateAccountRequestDto)
            }
        when (response.status.value) {
            in 200..299 -> {
                val responseBody: DeactivateUserAccountResponseDto = response.body()
                Log.v(
                    "DeactivatePharmacy: In Range 2xx",
                    "Successfully Deactivated ${responseBody.updatedData}"
                )
                Result.Success(data = responseBody)
            }

            else -> {
                val errorBody: NetworkMessage = response.body()
                Log.e("DeactivatePharmacy: Out of Range 2xx", errorBody.message)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("DeactivatePharmacy Exception:", e.message ?: "Unknown")
        Result.Error(NetworkError.UNKNOWN)
    }


    override suspend fun reactivatePharmacyAccount(
        token: String,
        pharmacyId: Int?
    ): Result<ReactivateUserAccountResponseDto, rootError> =
        try {
            val response: HttpResponse =
                client.post("${ApiRoutes.Admin.REACTIVATE_PHARMACY}/$pharmacyId") {
                    contentType(ContentType.Application.Json)
                    bearerAuth(token)
                }
            when (response.status.value) {
                in 200..299 -> {
                    val responseBody: ReactivateUserAccountResponseDto = response.body()
                    val responseBodyText: String = response.body()
                    Log.v(
                        "ReactivateAccount: In Range 2xx",
                        "Successfully Deactivated ${responseBody.updatedData}"
                    )
                    Log.v("ReactivateAccount: Body", responseBodyText)
                    Result.Success(data = responseBody)
                }

                else -> {
                    val errorBody: NetworkMessage = response.body()
                    Log.e("ReactivateAccount: Out of Range 2xx", errorBody.message)
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: Exception) {
            Log.e("ReactivateAccount Exception:", e.message ?: "Unknown")
            Result.Error(NetworkError.UNKNOWN)
        }

}