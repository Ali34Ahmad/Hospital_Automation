package com.example.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.use_cases.children.GetChildrenByNameUseCase
import com.example.model.child.ChildData
import com.example.model.guardian.PagedData
import com.example.utility.network.NetworkError
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

const val CHILDREN_SOURCE_TAG = "ChildrenPagingSource"

class ChildrenPagingSource(
    private val getChildrenByName: GetChildrenByNameUseCase,
    private val query: String,
): PagingSource<Int, ChildData>(){

    override fun getRefreshKey(state: PagingState<Int, ChildData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChildData> {
        try {
            //start refresh at page 1 if undefined
            val nextPageNumber = params.key?:1


            //initializing data per one page
            var nextKey: Int? = null
            var children = emptyList<ChildData>()

            val response = getChildrenByName(
                page = nextPageNumber,
                limit = params.loadSize,
                name = query
            )

            response.onSuccess{ data: PagedData<ChildData> ->
                Log.e(CHILDREN_SOURCE_TAG, "load: Success" )
                children = data.data
                nextKey = if(children.isEmpty()) null else data.page + 1
            }.onError {error: NetworkError->
                Log.e(CHILDREN_SOURCE_TAG, "load: Error $error" )
                return LoadResult.Error(Exception(error.name))
            }

            return LoadResult.Page(
                data = children,
                prevKey = null,
                nextKey = nextKey
            )
        }catch (e: Exception){
            Log.e(CHILDREN_SOURCE_TAG, "load :exception ${e.message}" )
            return LoadResult.Error(e)
        }
    }

}
