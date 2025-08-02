package com.example.data.paging_sources.appointment

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.doctor.toAppointmentData
import com.example.data.mapper.doctor.toAppointmentsStatisticsData
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.doctor.appointment.SortType
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
    private val token: String?,
    private val appointmentsApi: AppointmentsApiService,
    private val onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
) : PagingSource<Int, AppointmentData>(){
    override fun getRefreshKey(state: PagingState<Int, AppointmentData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AppointmentData> {
        try {
            if (token.isNullOrBlank()) {
                return LoadResult.Error(NetworkException(NetworkError.EMPTY_TOKEN))
            }
            val currentPage = params.key ?: 1
            var data = emptyList<AppointmentData>()
            appointmentsApi.showAppointments(
                token = token,
                params = appointmentState.toString(),
                page = currentPage,
                limit = params.loadSize,
                sort = sort.toString(),
                queryFilter = queryFilter,
                dateFilter = dateFilter
            ).onSuccess { response ->
                data = response.data.map { item ->
                    item.toAppointmentData()
                }
                onStatisticsChanged(response.appointmentStatistics.toAppointmentsStatisticsData())
            }.onError { error: NetworkError ->
                return LoadResult.Error(NetworkException(error))
            }
            Log.d("AppointmentPaging", "list size is ${data.size}")
            Log.d("AppointmentPaging", "next page : $currentPage")
            return LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
            return LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = if (data.isEmpty()) null else currentPage.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(NetworkException(NetworkError.UNKNOWN))
        }
    }

}

