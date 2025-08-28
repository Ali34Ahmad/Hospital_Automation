package com.example.data.paging_sources.medical_records

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.medical_record.toMedicalRecord
import com.example.data.mapper.user.toUserMainInfo
import com.example.model.medical_record.MedicalRecord
import com.example.model.user.UserMainInfo
import com.example.network.remote.medical_record.MedicalRecordsApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class MedicalRecordPagingSource(
    private val token: String,
    private val medicalRecordsApiService: MedicalRecordsApiService,
    private val onMainUserInfoChanged: (UserMainInfo) -> Unit,
): PagingSource<Int, MedicalRecord>(){
    override fun getRefreshKey(state: PagingState<Int, MedicalRecord>): Int? {
        return  state.anchorPosition?.let { anchorPosition->
            val anchorPage=state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MedicalRecord> {
        val nextPageNumber=params.key?:1
        try {
            var nextKey: Int? = null
            var list = emptyList<MedicalRecord>()

            medicalRecordsApiService.getAllMedicalRecordsForCurrentDoctor(
                token = token,
                page = nextPageNumber,
                limit = params.loadSize,
            ).onSuccess { response ->
                list = response.data.map { item ->
                    item.toMedicalRecord()
                }
                nextKey = if (list.isEmpty()) null else response.pagination.page + 1
                onMainUserInfoChanged(response.userMainInfoDto.toUserMainInfo())
            }.onError { error: NetworkError ->
                return LoadResult.Error(NetworkException(error))
            }
            Log.d("VaccinePaging", "next page : $nextPageNumber")
            return LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = nextKey
            )
        }catch (e: Exception){
            Log.e("VaccinePagingSource", "Error loading data", e)
            return LoadResult.Error(e)
        }
    }

}