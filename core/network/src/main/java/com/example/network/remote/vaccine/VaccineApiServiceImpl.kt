package com.example.network.remote.vaccine

import android.util.Log
import com.example.network.model.dto.vaccine.GenericVaccinationTableDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.enums.RoleDto
import com.example.network.model.response.vaccine.AddNewVaccineResponseDto
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import com.example.utility.network.Result
import com.example.utility.network.rootError

class VaccineApiServiceImpl(
    private val client: HttpClient,
) : VaccineApiService {
    override suspend fun addNewVaccine(
        token: String,
        vaccineDto: VaccineDto ,
    ): Result<AddNewVaccineResponseDto, rootError> = try {
        val response = client.post(ApiRoutes.Doctor.ADD_NEW_VACCINE) {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(vaccineDto)
        }
        when (response.status.value) {
            in 200..299 -> {
                val addVaccineResponse: AddNewVaccineResponseDto = response.body()
                Log.v("AddNewVaccineApi: in of range 2xx", addVaccineResponse.vaccine.id.toString())
                Result.Success(data = addVaccineResponse)
            }

            else -> {
                val errorBody=response.bodyAsText()
                Log.e("AddNewVaccineApi: Out of range 2xx", errorBody)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("AddNewVaccineApi Exception:", e.localizedMessage ?: "Unknown Error")
        Result.Error(NetworkError.UNKNOWN)
    }

    override suspend fun getVaccineById(
        token: String,
        id: Int,
        role: RoleDto
    ): Result<VaccineDto, rootError> = try {
        val response = client.post("${ApiRoutes.Doctor.GET_VACCINE_BY_ID}/$id") {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val getVaccineResponse: VaccineDto = response.body()
                Log.v("GetVaccineByIdApi: in of range 2xx", getVaccineResponse.toString())
                Result.Success(data = getVaccineResponse)
            }

            else -> {
                val errorBody=response.bodyAsText()
                Log.e("GetVaccineByIdApi: Out of range 2xx", errorBody)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("GetVaccineByIdApi Exception:", e.localizedMessage ?: "Unknown Error")
        Result.Error(NetworkError.UNKNOWN)
    }

    override suspend fun getGenericVaccinationTable(
        token: String,
    ): Result<GenericVaccinationTableDto, rootError> = try {
        val response = client.post(ApiRoutes.Doctor.GET_GENERIC_VACCINATION_TABLE) {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
        }
        when (response.status.value) {
            in 200..299 -> {
                val vaccinationTableResponse: GenericVaccinationTableDto = response.body()
                Log.v("GetVaccinationTableApi: in of range 2xx", vaccinationTableResponse.toString())
                Result.Success(data = vaccinationTableResponse)
            }

            else -> {
                val errorBody=response.bodyAsText()
                Log.e("GetVaccinationTableApi: Out of range 2xx", errorBody)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("GetVaccinationTableApi Exception:", e.localizedMessage ?: "Unknown Error")
        Result.Error(NetworkError.UNKNOWN)
    }


}