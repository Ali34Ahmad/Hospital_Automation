package com.example.network.remote.clinic

import com.example.network.model.response.clinics.GetClinicByIdResponse
import com.example.network.model.response.clinics.GetClinicsResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
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
    ): Result<GetClinicsResponse, NetworkError> =
        doApiCall<GetClinicsResponse>(
            tag = CLINIC_TAG
        ) {
            client.get(ApiRoutes.Clinic.SHOW_ALL_CLINICS){
                url{
                    parameter("page",page)
                    parameter("limit",limit)
                    name?.let { parameter("name",name) }
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
        }

    override suspend fun getClinicById(
        token: String,
        clinicId: Int,
    ): Result<GetClinicByIdResponse, NetworkError> =
        doApiCall<GetClinicByIdResponse>(
            tag = CLINIC_TAG
        ) {
            client.get(ApiRoutes.Clinic.SHOW_CLINIC+"/$clinicId") {
                bearerAuth(token)
            }
        }

}
