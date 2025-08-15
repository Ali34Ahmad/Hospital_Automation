package com.example.data.paging_sources.clinic

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.doctor.toClinicFullData
import com.example.model.doctor.clinic.ClinicFullData
import com.example.network.remote.clinic.ClinicApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

data class ClinicPagingSource(
    private val clinicApiService: ClinicApiService,
    private val token: String,
    private val name: String?,
): PagingSource<Int, ClinicFullData>(){
    override fun getRefreshKey(state: PagingState<Int, ClinicFullData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClinicFullData> {

        val nextPageNumber = params.key?:1
        var nextKey: Int? = null
        var clinics = emptyList<ClinicFullData>()

        clinicApiService.getAllClinics(
            token = token,
            page = nextPageNumber,
            limit = params.loadSize,
            name = name
        ).onSuccess { response->
            clinics = response.data.map{it.toClinicFullData()}
            nextKey = if(clinics.isEmpty()) null else response.pagination.page + 1
        }.onError { error:NetworkError->
            return LoadResult.Error(NetworkException(error))
        }
        return LoadResult.Page(
            data = clinics,
            prevKey = null,
            nextKey = nextKey
        )
    }
}
