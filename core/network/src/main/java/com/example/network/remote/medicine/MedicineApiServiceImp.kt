package com.example.network.remote.medicine

import android.util.Log
import com.example.network.model.response.medicine.GetAllMedicinesResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

const val MEDICINE_TAG = "MedicineApi"

class MedicineApiServiceImp(
    private val client: HttpClient
): MedicineApiService{
    override suspend fun getAllMedicines(
        token: String,
        page: Int,
        limit: Int,
        name: String?,
    ): Result<GetAllMedicinesResponse, NetworkError> {
        val response = try {
            client.get(ApiRoutes.Medicine.GET_MEDICINES){
                bearerAuth(token)
                url{
                    parameter("page",page)
                    parameter("limit",limit)
                    name?.let { parameter("name",name) }
                }
            }
        }catch (e: Exception){
            Log.e(MEDICINE_TAG,e.message.toString())
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when(response.status){
            HttpStatusCode.OK -> {
                Log.d(MEDICINE_TAG,"Success : ${response.bodyAsText()}")
                val body: GetAllMedicinesResponse = response.body()
                Result.Success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Log.e(MEDICINE_TAG, "Unauthorized : ${response.status.description}")
                Result.Error(NetworkError.UNAUTHORIZED)
            }
            else -> {
                Log.e(MEDICINE_TAG, "Unknown : ${response.status.description}")
                return Result.Error(NetworkError.UNKNOWN)
            }
        }

    }
}