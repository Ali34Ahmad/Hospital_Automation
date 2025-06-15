package com.example.network.remote.doctor

import com.example.network.model.response.doctor.appointments.ShowAppointmentsResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import java.time.LocalDate

interface DoctorApiService {
    suspend fun showAppointments(
        token: String,
        params: String,
        page: Int,
        limit: Int,
        sort: String,
        dateFilter: String? = null,
        queryFilter: String? = null
    ) : Result<ShowAppointmentsResponse, NetworkError>
}