package com.example.network.remote.add_residential_address

import com.example.network.model.request.AddressRequest
import com.example.network.model.response.AddressResponse
import com.example.network.model.response.NetworkMessage
import com.example.network.utility.ApiRoutes
import com.example.network.utility.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AddResidentialAddressApiImpl(
    private val client: HttpClient,
) : AddResidentialAddressApi {
    override suspend fun addResidentialAddress(
        addressRequest: AddressRequest
    ): Resource<AddressResponse> = try {
        val response = client.post(ApiRoutes.ADD_RESIDENTIAL_ADDRESS) {
            contentType(ContentType.Application.Json)
            setBody(addressRequest)
        }
        when (response.status) {
            HttpStatusCode.OK, HttpStatusCode.Created -> {
                val addressResponse: AddressResponse = response.body()
                Resource.Success(data = addressResponse)
            }
            else -> {
                val errorBody: NetworkMessage = response.body()
                Resource.Error(message = errorBody.message)
            }
        }
    } catch (e: Exception) {
        Resource.Error(message = "Network error during adding residential address: ${e.localizedMessage}")
    }
}