package com.example.child_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.child_profile.navigation.ChildProfileRoute
import com.example.domain.use_cases.children.GetChildByIdUseCase
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.ui_components.R
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
            updateScreenState(ScreenState.LOADING)
            getChildByIdUseCase(childId)
                .onSuccess{ result->
                    updateChild(result)
                    updateScreenState(ScreenState.SUCCESS)
                }.onError {
                    updateScreenState(ScreenState.ERROR)
                }
        }
    }

    fun onAction(action: ChildProfileUIAction){
        when(action){

            is ChildProfileUIAction.UpdateChild ->{
                updateChild(action.child)
            }
            is ChildProfileUIAction.UpdateState ->{
                updateScreenState(action.newState)
            }

            is ChildProfileUIAction.UpdateRefreshState -> {
                updateRefreshState(action.isRefreshing)
            }

            ChildProfileUIAction.Refresh -> {
                refreshData()
            }
            is ChildProfileUIAction.ShowToast ->{
                showToast(action.message)
            }
        }
    }
    private fun updateScreenState(newState: ScreenState) {
        _uiState.value = _uiState.value.copy(state = newState)
    }

    private fun showToast(message: UiText) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }

    private fun updateRefreshState(isRefreshing: Boolean) {
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }

    private fun updateChild(child: ChildFullData) {
        _uiState.value = _uiState.value.copy(child)
    }

    private fun refreshData(){
        viewModelScope.launch{
            updateRefreshState(true)
            getChildByIdUseCase(childId)
                .onSuccess{ result->
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    updateChild(result)
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

