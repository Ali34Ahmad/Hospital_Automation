package com.example.children.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.children.navigation.ChildrenRoute
import com.example.domain.use_cases.children.GetChildrenByGuardianIdUseCase
import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChildrenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getChildrenByGuardianIdUseCase: GetChildrenByGuardianIdUseCase,
): ViewModel() {
    //need to replace it with real one
    val id : Int = savedStateHandle.toRoute<ChildrenRoute>().userId

    private val _uiState = MutableStateFlow(ChildrenUIState(guardianId = id))
    val uiState : StateFlow<ChildrenUIState> = _uiState
        .onStart {
            loadUserData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ChildrenUIState(
                guardianId = id
            )
        )


    fun onAction(action: ChildrenUIAction){
        when(action){
            ChildrenUIAction.Retry -> {
                loadUserData()
            }
            is ChildrenUIAction.UpdateFetchingDataState -> {
                _uiState.value = _uiState.value.copy(
                    screenState = action.newState
                )
            }
        }
    }

    private fun loadUserData() = viewModelScope.launch{
        onAction(ChildrenUIAction.UpdateFetchingDataState(newState = ScreenState.LOADING))
        val response = getChildrenByGuardianIdUseCase(id)
        response.onSuccess{ data:List<ChildFullData> ->
            _uiState.value = uiState.value.copy(userChildren = data)
            onAction(ChildrenUIAction.UpdateFetchingDataState(newState = ScreenState.SUCCESS))
        }.onError{ error ->
            onAction(ChildrenUIAction.UpdateFetchingDataState(newState = ScreenState.ERROR))
        }
    }

}