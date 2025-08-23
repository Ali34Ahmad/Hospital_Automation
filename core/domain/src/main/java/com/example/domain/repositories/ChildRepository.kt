package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.guardian.GuardianData
import com.example.model.guardian.PagedData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import kotlinx.coroutines.flow.Flow


interface ChildRepository {

    suspend fun searchForChildrenAddedByEmployeeByName(
        name: String,
        employeeId: Int?
    ): Flow<PagingData<ChildData>>


    suspend fun getChildById(
        childId: Int
    ): Result<ChildFullData, NetworkError>

    suspend fun addChild(
        guardianId: Int,
        child: ChildFullData
    ): Result<ChildFullData,NetworkError>

    suspend fun getChildrenByGuardianId(
        guardianId: Int,
    ): Result<List<ChildFullData>,NetworkError>

    suspend fun searchForChildrenByName(
        name: String
    ): Flow<PagingData<ChildData>>

}