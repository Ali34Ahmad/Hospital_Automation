package com.example.network.remote.clinic

import android.util.Log
import com.example.network.model.response.clinics.GetClinicByIdResponse
import com.example.network.model.response.clinics.GetClinicsResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

const val CLINIC_TAG= "ClinicApiService"
class ClinicApiServiceImp(
    private val client: HttpClient,
): ClinicApiService{
    override suspend fun getAllClinics(
        token: String,
        page: Int,
        limit: Int,
        name: String?,
    ): Result<GetClinicsResponse, NetworkError> {
        val response = try {
            client.get(ApiRoutes.Clinic.SHOW_ALL_CLINICS){
                url{
                    parameter("page",page)
                    parameter("limit",limit)
                    name?.let { parameter("name",name) }
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        } catch (e: Exception) {
            Log.e(CLINIC_TAG, e.message.toString())
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(CLINIC_TAG,"Success : ${response.bodyAsText()}")
                val body: GetClinicsResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(CLINIC_TAG, "Unauthorized : ${response.status.description}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(CLINIC_TAG, "Unknown : ${response.status.description}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }

    override suspend fun getClinicById(
        token: String,
        clinicId: Int,
    ): Result<GetClinicByIdResponse, NetworkError> {
        val response = try {
            client.get(ApiRoutes.Clinic.SHOW_CLINIC+"/$clinicId") {
                bearerAuth(token)
            }
        }catch (e: Exception){
            Log.e(CLINIC_TAG,e.message.toString())
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d("$CLINIC_TAG get clinic by Id","""Success : ${response.bodyAsText()}""")
                val body: GetClinicByIdResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e("$CLINIC_TAG get clinic by Id", "Unauthorized : ${response.status.description}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e("$CLINIC_TAG get clinic by Id", "Unknown : ${response.status.description}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}
