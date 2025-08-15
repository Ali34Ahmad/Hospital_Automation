package com.example.data.paging_sources.employee

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.employee.toEmployeeData
import com.example.model.admin.EmploymentStatistics
import com.example.model.employee.EmployeeData
import com.example.model.employee.EmployeeState
import com.example.network.remote.admin.employee.AdminEmployeeApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess


class EmployeePagingSource (
    private val token: String?,
    private val query: String,
    private val status: EmployeeState,
    private val onStatisticsUpdated:(EmploymentStatistics)-> Unit,
    private val adminEmployeeApiService: AdminEmployeeApiService
): PagingSource<Int, EmployeeData>() {

    override fun getRefreshKey(state: PagingState<Int, EmployeeData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EmployeeData> {
        try {
            if (token.isNullOrBlank()) {
                return LoadResult.Error(NetworkException(NetworkError.EMPTY_TOKEN))
            }
            var currentPage = params.key?:1
            var data = emptyList<EmployeeData>()
            adminEmployeeApiService.getEmployees(
                token = token,
                page = currentPage,
                limit = params.loadSize,
                query = query,
                status = status.toString()
            ).onSuccess { result->
                onStatisticsUpdated(
                    EmploymentStatistics(
                        employedCount = result.employedCount,
                        stoppedCount = result.stoppedCount,
                        resignedCount = result.resignedCount
                    )
                )
                data = result.data.map { user->
                    user.toEmployeeData()
                }
            }.onError {
                return LoadResult.Error(NetworkException(it))
            }
            return LoadResult.Page(
                data = data,
                prevKey = if(currentPage==1) null else currentPage.minus(1),
                nextKey = if(data.isEmpty()) null else currentPage.plus(1)
            )
        }catch (_: Exception){
            return LoadResult.Error(NetworkException(NetworkError.UNKNOWN))
        }
    }
}