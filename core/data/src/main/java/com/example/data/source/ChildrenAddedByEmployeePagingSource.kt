package com.example.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.use_cases.children.GetChildrenAddedByEmployeeUseCase
import com.example.model.child.ChildData
import com.example.model.guardian.PagedData
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

data class ChildrenAddedByEmployeePagingSource(
    private val getChildrenAddedByEmployeeUseCase: GetChildrenAddedByEmployeeUseCase,
) : PagingSource<Int, ChildData>() {
    override fun getRefreshKey(state: PagingState<Int, ChildData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChildData> {
        try {

            val nextPageNumber = params.key ?: 1

            var nextKey: Int? = null
            var children: List<ChildData> = emptyList()
            Log.v(CHILDREN_SOURCE_TAG,"Start fetching data")
            val response = getChildrenAddedByEmployeeUseCase(
                page = nextPageNumber,
                limit = params.loadSize
            )
            response.onSuccess { pagedData: PagedData<ChildData> ->
                Log.v(CHILDREN_SOURCE_TAG,"Success fetching data")
                children = pagedData.data
                nextKey = if (children.isEmpty()) null else pagedData.page + 1
            }.onError { error ->
                Log.e(CHILDREN_SOURCE_TAG,"Error fetching data")
                return LoadResult.Error<Int, ChildData>(Exception(error.name))
            }
            return LoadResult.Page(
                data = children,
                nextKey = nextKey,
                prevKey = null
            )
        } catch (e: Exception) {
            return LoadResult.Error<Int, ChildData>(e)
        }
    }
}