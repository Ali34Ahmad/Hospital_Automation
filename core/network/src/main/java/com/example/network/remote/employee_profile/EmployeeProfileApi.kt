package com.example.network.remote.employee_profile

import com.example.network.model.response.ProfileResponse
import com.example.network.utility.Resource

interface EmployeeProfileApi {
    suspend fun getEmployeeInfo():Resource<ProfileResponse>
}