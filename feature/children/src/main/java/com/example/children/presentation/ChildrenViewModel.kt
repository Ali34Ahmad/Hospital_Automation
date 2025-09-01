package com.example.children.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.children.navigation.ChildrenRoute
import com.example.domain.use_cases.children.GetChildrenByGuardianIdUseCase
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.enums.ScreenState
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

class ChildrenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getChildrenByGuardianIdUseCase: GetChildrenByGuardianIdUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(
        ChildrenUIState(
            guardianId = savedStateHandle.toRoute<ChildrenRoute>().userId
        )
    )
    val uiState : StateFlow<ChildrenUIState> = _uiState
        .onStart {
            loadUserData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _uiState.value
        )


    fun onAction(action: ChildrenUIAction){
        when(action){
            ChildrenUIAction.Retry -> {
                loadUserData()
            }
            is ChildrenUIAction.UpdateScreenState -> {
                updateScreenState(action.newState)
            }

            ChildrenUIAction.Refresh -> {
                refreshData()
            }
            is ChildrenUIAction.ShowToast -> {
                showToast(action.message)
            }
            is ChildrenUIAction.UpdateRefreshState -> {
                updateRefreshState(action.isRefreshing)
            }
        }
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(
            screenState = newState
        )
    }
    private fun updateRefreshState(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun loadUserData() = viewModelScope.launch{
        updateScreenState(ScreenState.LOADING)
        val response = getChildrenByGuardianIdUseCase(uiState.value.guardianId)
        response.onSuccess{ data:List<ChildFullData> ->
            _uiState.value = uiState.value.copy(userChildren = data)
            updateScreenState(ScreenState.SUCCESS)
        }.onError{ error ->
            updateScreenState(ScreenState.ERROR)
        }
    }
    private fun showToast(message: UiText) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateChildren(children: List<ChildFullData>){
        _uiState.value = _uiState.value.copy(userChildren = children)
    }
    private fun refreshData(){
        viewModelScope.launch{
            updateRefreshState(true)
            getChildrenByGuardianIdUseCase(uiState.value.guardianId)
                .onSuccess{ result->
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    updateChildren(result)
                    if(uiState.value.screenState == ScreenState.ERROR){
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError {
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

}