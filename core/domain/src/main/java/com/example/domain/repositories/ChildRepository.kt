package com.example.domain.repositories

import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.guardian.PagedData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result

interface ChildRepository {
    suspend fun getChildrenByName(
        page: Int,
        limit: Int,
        name: String
    ): Result<PagedData<ChildData>, NetworkError>

    suspend fun getChildById(
        childId: Int
    ): Result<ChildFullData, NetworkError>

    suspend fun addChild(
        guardianId: Int,
        child: ChildFullData
    ): Result<ChildFullData,NetworkError>
}