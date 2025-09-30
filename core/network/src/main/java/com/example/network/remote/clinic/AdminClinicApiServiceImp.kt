package com.example.network.remote.clinic

import com.example.network.model.request.DeactivationReason
import com.example.network.model.response.clinics.GetFilteredClinicsResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

const val ADMIN_CLINIC_TAG = "CLINIC"
class AdminClinicApiServiceImp(
    private val client: HttpClient
): AdminClinicApiService {
    override suspend fun getClinics(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetFilteredClinicsResponse, NetworkError> =
        doApiCall(
            tag = ADMIN_CLINIC_TAG
        ) {
            client.get(ApiRoutes.Admin.ADMIN_GET_CLINICS){
                url {
                    parameters.append("type", "clinic")
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

    override suspend fun deactivateClinic(
        token: String,
        clinicId: Int,
        deactivationReason:String,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = ADMIN_CLINIC_TAG
        ) {
            client.post(ApiRoutes.Admin.DEACTIVATE_CLINIC+"/$clinicId") {
                contentType(ContentType.Application.Json)
                setBody(
                    DeactivationReason(deactivationReason)
                )
                bearerAuth(token)
            }
        }

    override suspend fun reactivateClinic(
        token: String,
        clinicId: Int,
    ): Result<Unit, NetworkError> =
        doApiCall(
            tag = ADMIN_CLINIC_TAG
        ) {
            client.post(ApiRoutes.Admin.REACTIVATE_CLINIC+"/$clinicId") {
                bearerAuth(token)
            }
        }
}