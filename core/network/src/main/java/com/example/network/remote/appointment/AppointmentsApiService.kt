package com.example.network.remote.appointment

import com.example.network.model.request.appointment.AddDiagnosisRequest
import com.example.network.model.response.appointments.AddDiagnosisResponse
import com.example.network.model.response.appointments.ShowAppointmentDetails
import com.example.network.model.response.appointments.ShowAppointmentsResponse
import com.example.network.model.response.appointments.UpdatedStateResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AppointmentsApiService {
    suspend fun showAppointments(
        token: String,
        params: String,
        page: Int,
        limit: Int,
        sort: String,
        dateFilter: String? = null,
        queryFilter: String? = null
    ) : Result<ShowAppointmentsResponse, NetworkError>

    suspend fun getAppointmentDetails(
        token: String,
        id: Int,
    ): Result<ShowAppointmentDetails, NetworkError>

    suspend fun updateAppointmentStateToPassed(
        token: String,
        appointmentId : Int
    ): Result<UpdatedStateResponse, NetworkError>

    suspend fun updateAppointmentStateToMissed(
        token: String,
        appointmentId : Int
    ): Result<UpdatedStateResponse, NetworkError>

    suspend fun addDiagnosis(
        token: String,
        appointmentId: Int,
        diagnosis: String
    ) : Result<AddDiagnosisResponse, NetworkError>
}