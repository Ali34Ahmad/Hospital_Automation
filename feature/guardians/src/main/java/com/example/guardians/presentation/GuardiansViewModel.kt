package com.example.guardians.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.users.GetGuardiansByChildIdUseCase
import com.example.guardians.navigation.GuardiansRoute
import com.example.model.enums.ScreenState
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GuardiansViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getGuardiansByChildIdUseCase: GetGuardiansByChildIdUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(
        GuardiansUIState(
            childId = savedStateHandle.toRoute<GuardiansRoute>().childId
        )
    )
    val uiState : StateFlow<GuardiansUIState> = _uiState.
    onStart {
        loadData()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        GuardiansUIState(
            childId = savedStateHandle.toRoute<GuardiansRoute>().childId
        )
    )
        fun onAction(action: GuardiansUIActions){
        when(action){
            GuardiansUIActions.Retry-> {
                TODO("need implementation")
            }
            is GuardiansUIActions.UpdateState->{
                _uiState.value = _uiState.value.copy(
                    state = action.newState
                )
            }
        }
    }
    private  fun loadData() = viewModelScope.launch{
        onAction(GuardiansUIActions.UpdateState(ScreenState.LOADING))
        getGuardiansByChildIdUseCase(_uiState.value.childId)
            .onSuccess { guardians ->
                onAction(GuardiansUIActions.UpdateState(ScreenState.SUCCESS))
                _uiState.value = _uiState.value.copy(data = guardians)
            }.onError {
                onAction(GuardiansUIActions.UpdateState(ScreenState.ERROR))
            }
    }
}
