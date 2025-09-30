package com.example.network.remote.add_residential_address

import android.util.Log
import com.example.network.model.enums.RoleDto
import com.example.network.model.request.AddressRequestDto
import com.example.network.model.response.AddAddressResponseDto
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val ADD_ADDRESS_API_TAG = "AddResidentialAddressApi"

class AddResidentialAddressApiServiceImpl(
    private val client: HttpClient,
) : AddResidentialAddressApiService {
    override suspend fun addResidentialAddress(
        token: String,
        addAddressRequestDto: AddressRequestDto,
        role: RoleDto,
    ): Result<AddAddressResponseDto, NetworkError> =
        doApiCall<AddAddressResponseDto>(
            tag = ADD_ADDRESS_API_TAG
        ) {
            Log.d(ADD_ADDRESS_API_TAG, "addResidentialAddress")

            client.post(ApiRoutes.getAddResidentialAddressEndPointFor(role)) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(addAddressRequestDto)
            }
        }
}