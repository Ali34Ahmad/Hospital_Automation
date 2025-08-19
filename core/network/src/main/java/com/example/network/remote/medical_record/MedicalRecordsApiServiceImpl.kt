package com.example.network.remote.medical_record

import android.util.Log
import com.example.network.model.response.medical_record.GetAllMedicalRecordsResponseDto
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
import io.ktor.http.contentType

class MedicalRecordsApiServiceImpl(
    private val client: HttpClient,
):MedicalRecordsApiService {
    override suspend fun getAllMedicalRecordsForCurrentDoctor(
        token: String,
        page: Int,
        limit: Int
    ): Result<GetAllMedicalRecordsResponseDto, NetworkError> = try {
        val response = client.get(ApiRoutes.Doctor.ALL_MEDICAL_RECORDS) {
            url{
                parameter("page",page)
                parameter("limit",limit)
            }
            contentType(ContentType.Application.Json)
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val responseText = response.bodyAsText()
                Log.v("GetAllMedicalRecordsApi${response.status.value}", responseText)

                val addVaccineResponse: GetAllMedicalRecordsResponseDto = response.body()
                Log.v("GetAllMedicalRecordsApi: in of range 2xx", addVaccineResponse.data.toString())
                Result.Success(data = addVaccineResponse)
            }

            else -> {
                val errorBody = response.bodyAsText()
                Log.e("GetAllMedicalRecordsApi: Out of range 2xx", errorBody)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    }catch (e: Exception){
        Log.e("GetAllMedicalRecordsApi Exception:", e.localizedMessage ?: "Unknown Error")
        Result.Error(NetworkError.UNKNOWN)
    }

}