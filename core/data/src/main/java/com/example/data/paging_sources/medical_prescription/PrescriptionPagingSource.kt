package com.example.data.paging_sources.medical_prescription

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.prescription.toPrescriptionWithUser
import com.example.data.mapper.user.toUserMainInfo
import com.example.model.prescription.PrescriptionWithUser
import com.example.model.user.UserMainInfo
import com.example.network.model.enums.RoleDto
import com.example.network.remote.prescription.PrescriptionApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class PrescriptionPagingSource(
    private val token: String,
    private val medicalPrescriptionsApiService: PrescriptionApiService,
    private val onMainUserInfoChanged: (UserMainInfo) -> Unit,
    private val role: RoleDto,
    private val patientId:Int?,
    private val childId:Int?,
    private val doctorId:Int?,
    private val name:String?,
) : PagingSource<Int, PrescriptionWithUser>() {
    override fun getRefreshKey(state: PagingState<Int, PrescriptionWithUser>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PrescriptionWithUser> {
        val nextPageNumber = params.key ?: 1
        try {
            var nextKey: Int? = null
            var list = emptyList<PrescriptionWithUser>()

            medicalPrescriptionsApiService.getAllPrescriptions(
                token = token,
                page = nextPageNumber,
                limit = params.loadSize,
                role = role,
                patientId = patientId,
                childId = childId,
                name = name,
                doctorId = doctorId,
            ).onSuccess { response ->
                list = response.data.map { item ->
                    item.toPrescriptionWithUser()
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
        } catch (e: Exception) {
            Log.e("VaccinePagingSource", "Error loading data", e)
            return LoadResult.Error(e)
        }
    }

}