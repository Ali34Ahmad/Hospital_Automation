package com.example.data.repositories.child

import com.example.data.constants.FAKE_TOKEN
import com.example.data.mapper.child.toAddChildRequest
import com.example.data.mapper.child.toChildData
import com.example.data.mapper.child.toChildFullData
import com.example.datastore.repositories.UserPreferenceRepository
import com.example.domain.repositories.ChildRepository
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.guardian.PagedData
import com.example.network.remote.child.ChildApiService
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.map
import kotlinx.coroutines.flow.collectLatest

class ChildRepositoryImp(
    private val childApiService: ChildApiService,
    private val dataStore: UserPreferenceRepository
): ChildRepository {
    var token: String? = null

    override suspend fun getChildrenByName(
        page: Int,
        limit: Int,
        name: String,
    ): Result<PagedData<ChildData>, NetworkError> {
        dataStore.userPreferencesFlow.collectLatest{data->
            token = data.token
        }
         //we get the response from the network then convert the response to the data class
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

}