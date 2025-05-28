package com.example.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.use_cases.users.GetGuardiansByNameUseCase
import com.example.model.guardian.GuardianData
import com.example.model.guardian.PagedData
import com.example.utility.network.NetworkError
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

private const val GUARDIANS_SOURCE_TAG = "GuardiansPagingSource"
class GuardiansPagingSource(
    private val getGuardiansByName: GetGuardiansByNameUseCase,
    private val query: String,
): PagingSource<Int, GuardianData>() {

    override fun getRefreshKey(state: PagingState<Int, GuardianData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GuardianData> {
        try {
            //start refresh at page 1 if undefined
            val nextPageNumber = params.key?:1


            var nextKey: Int? = null
            var guardians = emptyList<GuardianData>()

            val response = getGuardiansByName(
                page = nextPageNumber,
                limit = params.loadSize,
                name = query
            )

            response.onSuccess{ data: PagedData<GuardianData> ->
                Log.e(GUARDIANS_SOURCE_TAG, "load: Success" )
                guardians = data.data
                nextKey = if(guardians.isEmpty()) null else data.page + 1
            }.onError {error: NetworkError->
                Log.e(GUARDIANS_SOURCE_TAG, "load: Error $error" )
                return LoadResult.Error(Exception(error.name))
            }

            return LoadResult.Page(
                data = guardians,
                prevKey = null,
                nextKey = nextKey
            )

        }catch (e:Exception){
            Log.e(GUARDIANS_SOURCE_TAG, "load :exception ${e.message}" )
            return LoadResult.Error(e)
        }
    }

}