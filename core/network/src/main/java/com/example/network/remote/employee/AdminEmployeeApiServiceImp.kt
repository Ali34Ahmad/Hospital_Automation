package com.example.network.remote.employee

import com.example.network.model.response.doctor.GetEmployeesResponse
import com.example.network.utility.ApiRoutes
import com.example.network.utility.doApiCall
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get

const val ADMIN_EMPLOYEE_TAG = "EMPLOYEE"
class AdminEmployeeApiServiceImp(
    private val client: HttpClient
): AdminEmployeeApiService {
    override suspend fun getEmployees(
        token: String,
        query: String,
        status: String,
        page: Int,
        limit: Int,
    ): Result<GetEmployeesResponse, NetworkError> =
        doApiCall(
            tag = ADMIN_EMPLOYEE_TAG
        ) {
            client.get(ApiRoutes.Admin.GET_ALL_EMPLOYEES) {
                url {
                    parameters.append("employee_type", "employee")
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
}