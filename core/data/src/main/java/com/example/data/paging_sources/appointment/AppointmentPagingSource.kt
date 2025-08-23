package com.example.data.paging_sources.appointment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.doctor.toAppointmentData
import com.example.data.mapper.doctor.toAppointmentsStatisticsData
import com.example.data.mapper.enums.toRoleDto
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.doctor.appointment.SortType
import com.example.model.enums.Role
import com.example.network.remote.appointment.AppointmentsApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class AppointmentPagingSource(
    private val sort: SortType,
    private val queryFilter: String?,
    private val dateFilter: String?,
    private val appointmentState: AppointmentState,
    private val token: String,
    private val appointmentsApi: AppointmentsApiService,
    private val onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
    private val role: Role,
    private val doctorId: Int?
) : PagingSource<Int, AppointmentData>(){
    override fun getRefreshKey(state: PagingState<Int, AppointmentData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AppointmentData> {
        try {
            val currentPage = params.key ?: 1
            var data = emptyList<AppointmentData>()
            appointmentsApi.showAppointments(
                token = token,
                params = appointmentState.toString(),
                page = currentPage,
                limit = params.loadSize,
                sort = sort.toString(),
                queryFilter = queryFilter,
                dateFilter = dateFilter,
                doctorId = doctorId,
                roleDto = role.toRoleDto()
            ).onSuccess { response ->
                data = response.data.map { item ->
                    item.toAppointmentData()
                }
                onStatisticsChanged(response.appointmentStatistics.toAppointmentsStatisticsData())
            }.onError { error: NetworkError ->
                return LoadResult.Error(NetworkException(error))
            }
            return LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (_: Exception) {
            return LoadResult.Error(NetworkException(NetworkError.UNKNOWN))
        }
    }
}

