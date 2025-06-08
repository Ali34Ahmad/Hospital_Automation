package com.example.child_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.child_profile.navigation.ChildProfileRoute
import com.example.domain.use_cases.children.GetChildByIdUseCase
import com.example.model.enums.ScreenState
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChildProfileViewModel (
    private val getChildByIdUseCase: GetChildByIdUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val childId = savedStateHandle.toRoute<ChildProfileRoute>().childId

    private val _uiState = MutableStateFlow(ChildProfileUIState())
    val uiState: StateFlow<ChildProfileUIState> = _uiState
        .onStart {
            loadData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChildProfileUIState()
        )

    private fun loadData() {
        viewModelScope.launch{
            //1. loading ..
            onAction(
                ChildProfileUIAction.UpdateState(
                    newState = ScreenState.LOADING
                )
            )
            getChildByIdUseCase(childId)
                .onSuccess{ result->
                    //2. success
                    onAction(ChildProfileUIAction.UpdateChild(result))
                    onAction(
                        ChildProfileUIAction.UpdateState(
                            newState = ScreenState.SUCCESS
                        )
                    )
                }.onError {
                    //3.failure
                    onAction(
                        ChildProfileUIAction.UpdateState(
                            newState = ScreenState.ERROR
                        )
                    )
                }

        }
    }

    fun onAction(action: ChildProfileUIAction){
        when(action){
            is ChildProfileUIAction.UpdateChild ->{
                _uiState.value = _uiState.value.copy(child = action.child)
            }
            is ChildProfileUIAction.UpdateState ->{
                _uiState.value = _uiState.value.copy(state = action.newState)
            }
        }
    }

}

