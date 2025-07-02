package com.example.data.paging_sources.guardian

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.user.toGuardianData
import com.example.model.guardian.GuardianData
import com.example.model.guardian.PagedData
import com.example.network.remote.user.UserApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.map
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class GuardiansPagingSource(
    private val token: String?,
    private val userApiService: UserApiService,
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
            if (token == null){
                return LoadResult.Error(NetworkException(NetworkError.EMPTY_TOKEN))
            }
            //start refresh at page 1 if undefined
            val nextPageNumber = params.key?:1

            var nextKey: Int? = null
            var guardians = emptyList<GuardianData>()



            val response = userApiService.getUsersByName(
                token = token,
                page = nextPageNumber,
                limit = params.loadSize,
                name = query
            ).map { usersResponse ->
                val guardians = usersResponse.data.map { user->
                    user.toGuardianData()
                }
                PagedData<GuardianData>(
                    page = usersResponse.pagination.page,
                    data = guardians
                )
            }

            response.onSuccess { data: PagedData<GuardianData> ->
                guardians = data.data
                nextKey = if(guardians.isEmpty()) null else data.page + 1
            }.onError { error: NetworkError ->
                return LoadResult.Error(NetworkException(error))
            }

            return LoadResult.Page(
                data = guardians,
                prevKey = null,
                nextKey = nextKey
            )

        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }

}