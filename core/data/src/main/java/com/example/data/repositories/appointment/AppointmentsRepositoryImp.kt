package com.example.data.repositories.appointment

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.doctor.toAppointmentData
import com.example.data.paging_sources.appointment.AppointmentPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.AppointmentsRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.doctor.appointment.SortType
import com.example.model.role_config.RoleAppConfig
import com.example.network.remote.appointment.AppointmentsApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.UpdatedIds
import com.example.utility.network.map
import kotlinx.coroutines.flow.Flow

class AppointmentsRepositoryImp(
    private val dataStore: UserPreferencesRepository,
    private val appointmentsApi: AppointmentsApiService,
    private val role: RoleAppConfig,
): AppointmentsRepository {
    override suspend fun getAppointments(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        queryFilter: String?,
        dateFilter: String?,
        doctorId: Int?,
    ): Flow<PagingData<AppointmentData>> {

        //decide the sort type depending on appointment state.
        val sortType = if(appointmentState == AppointmentState.UPCOMMING)
            SortType.ASC else SortType.DESC

        //dataStore.executeFlowWithValidToken { token->
        return   Pager(
                config = PagingConfig(
                    pageSize = PagingConstants.PAGE_SIZE
                ),
                pagingSourceFactory = {
                    AppointmentPagingSource(
                        appointmentState = appointmentState,
                        token = FAKE_TOKEN,
                        appointmentsApi = appointmentsApi,
                        sort = sortType,
                        onStatisticsChanged = onStatisticsChanged,
                        queryFilter = queryFilter,
                        dateFilter = dateFilter,
                        role = role.role,
                        doctorId = doctorId,
                    )
                }
            ).flow
       // }

    }

    override suspend fun getAppointmentDetails(id: Int) =
        dataStore.executeWithValidTokenNetwork { token->
            appointmentsApi.getAppointmentDetails(id = id , token = token)
                .map { it.data.toAppointmentData() }
        }

    override suspend fun updateAppointmentStateToPassed(appointmentId: Int): Result<UpdatedIds, NetworkError> =
        dataStore.executeWithValidTokenNetwork { token ->
            appointmentsApi.updateAppointmentStateToPassed(
                token = token,
                appointmentId = appointmentId
            ).map { it.updatedState }
        }


    override suspend fun updateAppointmentStateToMissed(appointmentId: Int): Result<UpdatedIds, NetworkError> =
        dataStore.executeWithValidTokenNetwork { token->
            appointmentsApi.updateAppointmentStateToMissed(
                token = token,
                appointmentId = appointmentId
            ).map { it.updatedState }
        }

    override suspend fun addDiagnosis(
        appointmentId: Int,
        diagnosis: String,
    ): Result<Int, NetworkError> =
        dataStore.executeWithValidTokenNetwork { token->
            appointmentsApi.addDiagnosis(
                token = token,
                appointmentId = appointmentId,
                diagnosis = diagnosis
            ).map { it.updatedData[0] }
        }
}