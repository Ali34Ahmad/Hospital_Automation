package com.example.network.remote.pharmacy

import com.example.network.model.response.pharmacy.GetFilteredPharmaciesResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get

const val ADMIN_PHARMACY_TAG = "PHARMACY"
class AdminPharmacyApiServiceImp(
    private val client: HttpClient
): AdminPharmacyApiService {
    override suspend fun getPharmacies(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetFilteredPharmaciesResponse, NetworkError> =
        doApiCall(
            tag = ADMIN_PHARMACY_TAG
        ) {
            client.get(ApiRoutes.Admin.ADMIN_GET_PHARMACIES) {
                url {
                    parameters.append("type", "pharmacy")
                    parameters.append("state",status)
                    if(query.isNotBlank()){
                        parameters.append("name", query)
                    }
                    parameters.append("page", page.toString())
                    parameters.append("limit", limit.toString())
                }
                bearerAuth(token)
            }
        }
}