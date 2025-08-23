package com.example.data.paging_sources.medicine

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.doctor.toMedicineSummaryData
import com.example.model.medicine.MedicineSummaryData
import com.example.network.remote.medicine.MedicineApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class MedicineSummaryPagingSource(
    private val medicineApi: MedicineApiService,
    private val token: String,
    private val name: String,
    private val pharmacyId: Int,
): PagingSource<Int, MedicineSummaryData>(){
    override fun getRefreshKey(
        state: PagingState<Int, MedicineSummaryData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MedicineSummaryData> {
        val currentPage = params.key ?: 1
        var data = emptyList<MedicineSummaryData>()
        medicineApi.searchForMedicinesByPharmacyId(
            token = token,
            pharmacyId = pharmacyId,
            page = currentPage,
            limit = params.loadSize,
            name = name
        ).onSuccess { response->
            data = response.data.map{ medicineSummaryDto ->
                medicineSummaryDto.toMedicineSummaryData()
            }
        }.onError { error: NetworkError ->
            return LoadResult.Error(NetworkException(error))
        }

        return LoadResult.Page(
            data = data,
            prevKey = if (currentPage==1) null else currentPage.minus(1),
            nextKey = if (data.isEmpty()) null else currentPage.plus(1)
        )
    }
}