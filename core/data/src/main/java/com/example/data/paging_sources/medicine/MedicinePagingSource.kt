package com.example.data.paging_sources.medicine

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.doctor.toMedicineData
import com.example.model.medicine.MedicineData
import com.example.network.remote.medicine.MedicineApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess


class MedicinePagingSource(
    private val token: String?,
    private val medicineApi: MedicineApiService,
    private val name: String?
): PagingSource<Int, MedicineData>() {
    override fun getRefreshKey(state: PagingState<Int, MedicineData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closetPage = state.closestPageToPosition(anchorPosition)
            closetPage?.prevKey?.plus(1)?: closetPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MedicineData> {
        val currentPage = params.key ?: 1

        if (token.isNullOrBlank()){
            return LoadResult.Error(NetworkException(NetworkError.EMPTY_TOKEN))
        }
        var data = emptyList<MedicineData>()
        medicineApi.getAllMedicines(
            token = token,
            page = 0,
            limit = params.loadSize,
            name = name
        ).onSuccess {
            data = it.data.map { it.toMedicineData() }
        }.onError { error: NetworkError ->
            return LoadResult.Error<Int, MedicineData>(NetworkException(error))
        }

        return LoadResult.Page(
            data = data,
            prevKey = if (currentPage==1) null else currentPage.minus(1),
            nextKey = if (data.isEmpty()) null else currentPage.plus(1)
        )
    }
}