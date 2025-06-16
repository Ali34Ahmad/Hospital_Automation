package com.example.data.repositories.doctor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constants.FAKE_TOKEN
import com.example.data.paging_sources.appointment.AppointmentPagingSource
import com.example.domain.model.constants.PagingConstants
import com.example.domain.repositories.DoctorRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.doctor.appointment.SortType
import com.example.network.remote.doctor.DoctorApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class DoctorRepositoryImp(
    private val dataStore: UserPreferencesRepository,
    private val doctorApiService: DoctorApiService
): DoctorRepository {
    override suspend fun getAppointments(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        queryFilter: String?,
        dateFilter: String?
    ): Flow<PagingData<AppointmentData>> {
        //don't forget use the real token
        val token = dataStore.userPreferencesDataStoreFlow.first().token

        //decide the sort type depending on appointment state.
        val sortType = if(appointmentState == AppointmentState.UPCOMMING)
            SortType.ASC else SortType.DESC

        //create a flow of paging data of AppointmentData class
        return Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE
            ),
            pagingSourceFactory = {
                AppointmentPagingSource(
                    appointmentState = appointmentState,
                    token = FAKE_TOKEN,
                    doctorApiService = doctorApiService,
                    sort = sortType,
                    onStatisticsChanged = onStatisticsChanged,
                    queryFilter = queryFilter,
                    dateFilter = dateFilter
                )
            }
        ).flow
    }
}