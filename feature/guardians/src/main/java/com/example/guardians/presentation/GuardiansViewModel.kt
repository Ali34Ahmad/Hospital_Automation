package com.example.guardians.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.users.GetGuardiansByChildIdUseCase
import com.example.guardians.navigation.GuardiansRoute
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianData
import com.example.ui_components.R
import com.example.util.UiText
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
            GuardiansUIActions.Refresh-> {
                refreshData()
            }
            is GuardiansUIActions.UpdateState->{
                updateScreenState(action.newState)
            }

            is GuardiansUIActions.ShowToast -> {
                showToast(action.message)
            }
            is GuardiansUIActions.UpdateRefreshState -> {
                updateRefreshState(action.isRefreshing)
            }
        }
    }
    private fun updateGuardians(guardians: List<GuardianData>){
        _uiState.value = _uiState.value.copy(data = guardians)
    }
    private  fun loadData() = viewModelScope.launch{
        updateScreenState(ScreenState.LOADING)
        getGuardiansByChildIdUseCase(_uiState.value.childId)
            .onSuccess { guardians ->
                updateScreenState(ScreenState.SUCCESS)
                updateGuardians(guardians)
            }.onError {
                updateScreenState(ScreenState.ERROR)
            }
    }
    private fun showToast(message: UiText) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateRefreshState(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(
            state = newState
        )
    }
    private fun refreshData(){
        viewModelScope.launch{
            updateRefreshState(true)
            getGuardiansByChildIdUseCase(_uiState.value.childId)
                .onSuccess{ result->
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    updateGuardians(result)
                    if(uiState.value.state == ScreenState.ERROR){
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError {
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }
}
