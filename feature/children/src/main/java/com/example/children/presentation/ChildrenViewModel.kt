package com.example.children.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.children.navigation.ChildrenRoute
import com.example.data.constants.FAKE_ID
import com.example.data.source.ChildrenAddedByEmployeePagingSource
import com.example.domain.model.constants.SourcesConstants
import com.example.domain.use_cases.children.GetChildrenAddedByEmployeeUseCase
import com.example.domain.use_cases.children.GetChildrenByGuardianIdUseCase
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.enums.FetchingDataState
import com.example.model.helper.IdType
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChildrenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getChildrenByGuardianIdUseCase: GetChildrenByGuardianIdUseCase,
    private val getChildrenAddedByEmployeeUseCase: GetChildrenAddedByEmployeeUseCase,
): ViewModel() {
    //need to replace it with real one
    val id : Int = savedStateHandle.toRoute<ChildrenRoute>().userId
    val type: IdType = savedStateHandle.toRoute<ChildrenRoute>().type

    private val _uiState = MutableStateFlow(ChildrenUIState(type = type))
    val uiState : StateFlow<ChildrenUIState> = _uiState
        .onStart {
            if(type == IdType.USER)
                loadUserData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ChildrenUIState(type = type)
        )

    val employeeChildren: Flow<PagingData<ChildData>> =  getEmployeeChildrenFlow()


    private fun getEmployeeChildrenFlow(): Flow<PagingData<ChildData>> {
        return if(type== IdType.EMPLOYEE){
            Pager(
                config = PagingConfig(
                    pageSize = SourcesConstants.PAGE_SIZE,
                ),
                pagingSourceFactory = {
                    ChildrenAddedByEmployeePagingSource(
                        getChildrenAddedByEmployeeUseCase = getChildrenAddedByEmployeeUseCase
                    )
                }
            ).flow.cachedIn(viewModelScope)
        }else{
            flowOf(PagingData.empty())
        }
    }

    fun onAction(action: ChildrenUIAction){
        when(action){
            ChildrenUIAction.Retry -> {
                loadUserData()
            }
            is ChildrenUIAction.UpdateFetchingDataState -> {
                _uiState.value = _uiState.value.copy(
                    fetchingDataState = action.newState
                )
            }

            ChildrenUIAction.NavigateUp -> Unit
            is ChildrenUIAction.NavigateToChildProfile -> Unit
        }
    }

    private fun loadUserData() = viewModelScope.launch{
        onAction(ChildrenUIAction.UpdateFetchingDataState(newState = FetchingDataState.DOING_PROCESS))
        val response = getChildrenByGuardianIdUseCase(id)
        response.onSuccess{ data:List<ChildFullData> ->
            _uiState.value = uiState.value.copy(userChildren = data)
            onAction(ChildrenUIAction.UpdateFetchingDataState(newState = FetchingDataState.Success))

        }.onError{ error ->
            onAction(ChildrenUIAction.UpdateFetchingDataState(newState = FetchingDataState.ERROR))
        }
    }

}