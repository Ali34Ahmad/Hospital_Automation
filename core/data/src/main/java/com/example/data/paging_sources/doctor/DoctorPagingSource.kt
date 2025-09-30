package com.example.data.paging_sources.doctor

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.doctor.toDoctorData
import com.example.model.admin.EmploymentStatistics
import com.example.model.doctor.DoctorData
import com.example.model.employee.EmployeeState
import com.example.network.remote.doctor.AdminDoctorApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.NetworkException
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class DoctorPagingSource (
    private val token: String,
    private val query: String,
    private val status: EmployeeState,
    private val onStatisticsUpdated:(EmploymentStatistics)-> Unit,
    private val adminDoctorApiService: AdminDoctorApiService,
    private val clinicId: Int?
): PagingSource<Int, DoctorData>() {

    override fun getRefreshKey(state: PagingState<Int, DoctorData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DoctorData> {
        try {
            val currentPage = params.key?:1
            var data = emptyList<DoctorData>()
            //If the user pass the clinic id we use the admin doctor api service to get the doctors by clinic id
            //else we returns all the doctors in the system.
            val result = if(clinicId==null){
                adminDoctorApiService.getDoctors(
                    token = token,
                    page = currentPage,
                    limit = params.loadSize,
                    query = query,
                    status = status.toString()
                )
            } else{
                adminDoctorApiService.getDoctorsByClinic(
                    token = token,
                    page = currentPage,
                    limit = params.loadSize,
                    query = query,
                    status = status.toString(),
                    clinicId = clinicId
                )
            }

                result.onSuccess { result->
                onStatisticsUpdated(
                    EmploymentStatistics(
                        employedCount = result.employedCount,
                        stoppedCount = result.stoppedCount,
                        resignedCount = result.resignedCount
                    )
                )
                data = result.data.map { user->
                    user.toDoctorData()
                }
            }.onError {
                return LoadResult.Error(NetworkException(it))
            }
            return LoadResult.Page(
                data = data,
                prevKey = if(currentPage==1) null else currentPage.minus(1),
                nextKey = if(data.isEmpty()) null else currentPage.plus(1)
            )
        }catch (_: Exception){
            return LoadResult.Error(NetworkException(NetworkError.UNKNOWN))
        }
    }
}