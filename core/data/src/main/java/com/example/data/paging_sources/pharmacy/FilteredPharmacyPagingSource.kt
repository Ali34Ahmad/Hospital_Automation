package com.example.data.paging_sources.pharmacy

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.pharmacy.toPharmacyData
import com.example.model.admin.DepartmentState
import com.example.model.admin.DepartmentStatistics
import com.example.model.pharmacy.PharmacyData
import com.example.network.remote.pharmacy.AdminPharmacyApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class FilteredPharmacyPagingSource(
    private val adminPharmacyApi: AdminPharmacyApiService,
    private val token: String,
    private val query: String,
    private val status: DepartmentState,
    private val onStatisticsUpdated: (DepartmentStatistics)-> Unit,
    ): PagingSource<Int, PharmacyData>() {
    override fun getRefreshKey(state: PagingState<Int, PharmacyData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PharmacyData> {
        try {
            var currentPage = params.key?:1
            var data = emptyList<PharmacyData>()
            adminPharmacyApi.getPharmacies(
                token = token,
                page = currentPage,
                limit = params.loadSize,
                query = query,
                status = status.toString()
            ).onSuccess { result->
                onStatisticsUpdated(
                    DepartmentStatistics(
                        activeCount = result.activeCount,
                        stoppedCount = result.stoppedCount,
                        previousCount = result.previousCount
                    )
                )
                data = result.data.map { user->
                    user.toPharmacyData()
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