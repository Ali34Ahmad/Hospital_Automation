package com.example.data.paging_sources.vaccine

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.vaccine.toVaccineData
import com.example.model.enums.Role
import com.example.model.vaccine.VaccineData
import com.example.network.model.enums.RoleDto
import com.example.network.remote.vaccine.VaccineApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class VaccinePagingSource(
    private val token: String?,
    private val vaccineApi: VaccineApiService,
    private val role: RoleDto,
) : PagingSource<Int, VaccineData>() {
    override fun getRefreshKey(state: PagingState<Int, VaccineData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VaccineData> {
        val nextPageNumber = params.key ?: 1
        if (token == null) {
            return LoadResult.Error(NetworkException(NetworkError.EMPTY_TOKEN))
        }
        try {
            var nextKey: Int? = null
            var list = emptyList<VaccineData>()

            vaccineApi.getAllVaccines(
                token = token,
                page = nextPageNumber,
                limit = params.loadSize,
                role=role,
            ).onSuccess { response ->
                list = response.data.map { item ->
                    item.toVaccineData()
                }
                nextKey = if (list.isEmpty()) null else response.pagination.page + 1
            }.onError { error: NetworkError ->
                return LoadResult.Error(NetworkException(error))
            }
            Log.d("VaccinePaging", "next page : $nextPageNumber")
            return LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            Log.e("VaccinePagingSource", "Error loading data", e)
            return LoadResult.Error(e)
        }
    }
}