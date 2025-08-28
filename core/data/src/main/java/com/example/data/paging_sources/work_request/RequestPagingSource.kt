package com.example.data.paging_sources.work_request

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.work_request.toRequestStateDto
import com.example.data.mapper.work_request.toSingleRequestResponse
import com.example.model.work_request.RequestState
import com.example.model.work_request.SingleRequestResponse
import com.example.network.remote.work_request.WorkRequestApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess


class WorkRequestResponsePagingSource(
    private val token: String?,
    private val requestApi: WorkRequestApiService,
) : PagingSource<Int, SingleRequestResponse>() {
    override fun getRefreshKey(state: PagingState<Int, SingleRequestResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleRequestResponse> {
        val nextPageNumber = params.key ?: 1
        if (token == null) {
            return LoadResult.Error(NetworkException(NetworkError.EMPTY_TOKEN))
        }
        try {
            var nextKey: Int? = null
            var list = emptyList<SingleRequestResponse>()

            requestApi.getRequests(
                token = token,
                page = nextPageNumber,
                limit = params.loadSize,
            ).onSuccess { response ->
                list = response.data.map { item ->
                    item.toSingleRequestResponse()
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