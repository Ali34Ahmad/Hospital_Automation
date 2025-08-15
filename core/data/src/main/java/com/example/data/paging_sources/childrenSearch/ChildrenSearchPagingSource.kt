package com.example.data.paging_sources.childrenSearch

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.child.toChildData
import com.example.model.child.ChildData
import com.example.model.guardian.PagedData
import com.example.network.remote.child.ChildApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.map
import com.example.utility.network.onError
import com.example.utility.network.onSuccess


class ChildrenSearchPagingSource(
    private val token: String,
    private val childApiService: ChildApiService,
    private val query: String,
): PagingSource<Int, ChildData>(){

    override fun getRefreshKey(state: PagingState<Int, ChildData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChildData> {
        val nextPageNumber = params.key?:1


        var nextKey: Int? = null
        var children = emptyList<ChildData>()

        val response = childApiService.getChildrenByName(
            page = nextPageNumber,
            limit = params.loadSize,
            name = query,
            token = token
        ).map { response->
            val children = response.data.map { it.toChildData() }
            PagedData(
                data = children,
                page = response.pagination.page
            )
        }

        response.onSuccess{ data: PagedData<ChildData> ->
            children = data.data
            nextKey = if(children.isEmpty()) null else data.page + 1
        }.onError {error: NetworkError->
            return LoadResult.Error(NetworkException(error))
        }

        return LoadResult.Page(
            data = children,
            prevKey = null,
            nextKey = nextKey
        )

    }
}
