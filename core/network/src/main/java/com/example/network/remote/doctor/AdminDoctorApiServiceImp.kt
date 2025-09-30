package com.example.network.remote.doctor

import com.example.network.model.response.doctor.GetEmployeesResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get


private const val ADMIN_DOCTOR_TAG = "DOCTOR API SERVICE"
class AdminDoctorApiServiceImp(
    private val client: HttpClient
): AdminDoctorApiService {
    override suspend fun getDoctors(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetEmployeesResponse, NetworkError> =
        doApiCall(
            tag = ADMIN_DOCTOR_TAG
        ) {
            client.get(ApiRoutes.Admin.GET_ALL_DOCTORS) {
                url {
                    parameters.append("employee_type", "doctor")
                    parameters.append("employment_state",status)
                    if(query.isNotBlank()){
                        parameters.append("name", query)
                    }
                    parameters.append("page", page.toString())
                    parameters.append("limit", limit.toString())
                }
                bearerAuth(token)
            }
        }

    override suspend fun getDoctorsByClinic(
        token: String,
        query: String,
        clinicId: Int,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetEmployeesResponse, NetworkError> =
        doApiCall(
            tag = ADMIN_DOCTOR_TAG
        ) {
            client.get(ApiRoutes.Admin.GET_DOCTORS_IN_SPECIFIC_CLINIC+"/$clinicId") {
                url {
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