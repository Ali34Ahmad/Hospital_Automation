package com.example.network.remote.appointment

import com.example.network.model.enums.RoleDto
import com.example.network.model.response.appointments.AddDiagnosisResponse
import com.example.network.model.response.appointments.ShowAppointmentDetails
import com.example.network.model.response.appointments.ShowAppointmentsResponse
import com.example.network.model.response.appointments.UpdatedStateResponse
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface AppointmentsApiService {
    /**
     * Get Filtered Appointments for a given doctor
     * when a doctor use this feature he will be able to filter the appointments without
     * passing its id.
     * @author Ali Mansoura
     */
    suspend fun showAppointments(
        token: String,
        params: String,
        page: Int,
        limit: Int,
        sort: String,
        dateFilter: String? = null,
        queryFilter: String? = null,
        doctorId: Int?,
        roleDto: RoleDto
    ) : Result<ShowAppointmentsResponse, NetworkError>

    suspend fun getAppointmentDetails(
        token: String,
        id: Int,
        roleDto: RoleDto
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