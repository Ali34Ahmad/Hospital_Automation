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
import com.example.network.remote.appointment.AppointmentsApiServiceImp
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
        val nextPageNumber = params.key?:1
        if (token == null) {
            return LoadResult.Error(NetworkException(NetworkError.EMPTY_TOKEN))
        }
        try {
            var nextKey: Int? = null
            var list = emptyList<AppointmentData>()

            appointmentsApi.showAppointments(
                token = token,
                params = appointmentState.toString(),
                page = nextPageNumber,
                limit = params.loadSize,
                sort = sort.toString(),
                queryFilter = queryFilter,
                dateFilter = dateFilter
            ).onSuccess { response->
                list = response.data.map { item->
                    item.toAppointmentData()
                }
                nextKey = if (list.isEmpty()) null else response.pagination.page+1
                onStatisticsChanged(response.appointmentStatistics.toAppointmentsStatisticsData())
            }.onError {error: NetworkError->
                return LoadResult.Error(NetworkException(error))
            }
            Log.d("AppointmentPaging","next page : $nextPageNumber")
            return LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            Log.e("AppointmentPagingSource", "Error loading data", e)
            return LoadResult.Error(e)
        }
    }

}