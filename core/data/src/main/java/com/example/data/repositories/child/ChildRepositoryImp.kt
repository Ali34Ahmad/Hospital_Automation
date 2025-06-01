package com.example.data.repositories.child

import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.child.toAddChildRequest
import com.example.data.mapper.child.toChildData
import com.example.data.mapper.child.toChildFullData
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.domain.repositories.ChildRepository
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.guardian.PagedData
import com.example.network.remote.child.ChildApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ChildRepositoryImp(
    private val childApiService: ChildApiService,
    private val dataStore: UserPreferencesRepository
): ChildRepository {

    override suspend fun getChildrenByName(
        page: Int,
        limit: Int,
        name: String,
    ): Result<PagedData<ChildData>, NetworkError> {
        val token = dataStore.userPreferencesFlow.map { it.token }.first()
        token?.let {
            return childApiService.getChildrenByName(
                page = page,
                limit = limit,
                token = it,
                name = name
            ).map { response->
                val children = response.data.map{it.toChildData()}
                val page = response.pagination.page
                PagedData<ChildData>(
                    page = page,
                    data = children
                )
            }
        }?:return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)
    }

    override suspend fun getChildById(childId: Int): Result<ChildFullData, NetworkError> {
        return childApiService.getChildProfile(
            id = childId,
            token = FAKE_TOKEN
        ).map { data->
            data.toChildFullData()
        }

    }

    override suspend fun addChild(
        guardianId: Int,
        child: ChildFullData,
    ): Result<ChildFullData, NetworkError> {
        return childApiService.addChild(
            token = FAKE_TOKEN,
            guardianId = guardianId,
            child = child.toAddChildRequest()
        ).map {
            it.toChildFullData()
        }
    }


    override suspend fun getChildrenByGuardianId(guardianId: Int): Result<List<ChildFullData>, NetworkError> {
        val token = dataStore.userPreferencesFlow.map { it.token }.first()
        if (token == null)
            return Result.Error<NetworkError>(NetworkError.EMPTY_TOKEN)
        val result  = childApiService.getChildrenByGuardianId(
            token = token,
            guardianId = guardianId
        ).map {
            it.data.map { it.child.toChildFullData() }
        }
        return result
    }

    override suspend fun getChildrenAddedByEmployee(
        page: Int,
        limit: Int,
    ): Result<PagedData<ChildData>, NetworkError> {
        val token = dataStore.userPreferencesFlow.map { it.token }.first()
        return childApiService.getChildrenAddedByEmployee(
            token = FAKE_TOKEN,
            page = page,
            limit = limit
        ).map { response->
            val children = response.data.map{it.toChildData()}
            val page = response.pagination.page
            PagedData<ChildData>(
                page = page,
                data = children
            )
        }
    }

    override suspend fun searchChildrenAddedByEmployeeByName(
        page: Int,
        limit: Int,
        name: String,
    ): Result<PagedData<ChildData>, NetworkError> =
        childApiService.searchForChildrenAddedByEmployee(
            token = FAKE_TOKEN,
            page = page,
            limit = limit,
            name = name
        ).map {
            val children = it.data.map{it.toChildData()}
            val page = it.pagination.page
            PagedData<ChildData>(
                page = page,
                data = children
            )
        }

}