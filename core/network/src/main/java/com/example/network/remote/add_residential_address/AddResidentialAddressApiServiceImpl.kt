package com.example.network.remote.add_residential_address

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.AddressRequestDto
import com.example.network.model.response.AddAddressResponseDto
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

class AddResidentialAddressApiServiceImpl(
    private val client: HttpClient,
) : AddResidentialAddressApiService {
    override suspend fun addResidentialAddress(
        token: String,
        addAddressRequestDto: AddressRequestDto,
        role: RoleDto,
    ): Result<AddAddressResponseDto, rootError> = try {
        val response = client.post(ApiRoutes.getAddResidentialAddressEndPointFor(role)) {
            contentType(ContentType.Application.Json)
            bearerAuth(token)
            setBody(addAddressRequestDto)
        }
        when (response.status.value) {
            in 200..299 -> {
                val addressResponse: AddAddressResponseDto = response.body()
                Log.v("AddResidentialAddressApi: in of range 2xx", addressResponse.updatedData.toString())
                Result.Success(data = addressResponse)
            }

            else -> {
                val errorBody=response.bodyAsText()
                Log.e("AddResidentialAddressApi: Out of range 2xx", errorBody)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("AddResidentialAddressApi Exception:", e.localizedMessage ?: "Unknown Error")
        Result.Error(NetworkError.UNKNOWN)
    }
}