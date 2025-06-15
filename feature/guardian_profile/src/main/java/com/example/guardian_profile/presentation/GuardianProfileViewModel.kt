package com.example.guardian_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.users.AddGuardianToChildUseCase
import com.example.domain.use_cases.users.GetGuardianByIdUseCase
import com.example.guardian_profile.navigation.GuardianProfileRoute
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianFullData
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.constants.DurationConstants
import com.example.utility.network.NetworkError
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GuardianProfileViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val guardianProfileUseCase: GetGuardianByIdUseCase,
    private val addGuardianToChildUseCase: AddGuardianToChildUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(
        GuardianProfileUIState(
        childId = savedStateHandle.toRoute<GuardianProfileRoute>().childId,
        guardianId = savedStateHandle.toRoute<GuardianProfileRoute>().guardianId,
            userProfileMode = savedStateHandle.toRoute<GuardianProfileRoute>().userProfileMode
        )
    )

    val uiState: StateFlow<GuardianProfileUIState> = _uiState
        .onStart {
            loadInitialData()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            GuardianProfileUIState(
                childId = savedStateHandle.toRoute<GuardianProfileRoute>().childId,
                guardianId = savedStateHandle.toRoute<GuardianProfileRoute>().guardianId,
                userProfileMode = savedStateHandle.toRoute<GuardianProfileRoute>().userProfileMode
            )
        )

    fun onAction(action: GuardianProfileActions){
        when(action){
            //network call to add guardian to child
            GuardianProfileActions.SetAsGuardian -> {
                _uiState.value.childId?.let {
                    updateScreenState(ScreenState.LOADING)
                    viewModelScope.launch {
                        addGuardianToChildUseCase(
                            childId = it,
                            userId = _uiState.value.guardianId
                        ).onSuccess{
                            updateScreenState(ScreenState.SUCCESS)
                        }
                            .onError {error->
                                updateScreenState(ScreenState.ERROR)
                                setErrorMessage(error)
                                delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
                                resetButtonState()
                            }
                    }
                }
            }
            is GuardianProfileActions.UpdateScreenState -> {
                updateScreenState(action.state)
            }
            is GuardianProfileActions.UpdateBottomBarState ->{
                _uiState.value = _uiState.value.copy(
                    bottomBarState = action.state
                )
            }
            is GuardianProfileActions.UpdateGuardianData ->{
                updateGuardian(action.data)
            }

            GuardianProfileActions.Refresh ->{
                refreshData()
            }
            is GuardianProfileActions.ShowToast -> {
                showToast(action.message)
            }
            is GuardianProfileActions.UpdateRefreshState ->{
                updateRefreshState(action.isRefreshing)
            }
        }
    }
    private fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateScreenState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    private fun updateRefreshState(isRefreshing: Boolean) {
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }
    private fun loadInitialData(){
        _uiState.value = _uiState.value.copy(screenState = ScreenState.LOADING)
        viewModelScope.launch {
            guardianProfileUseCase(savedStateHandle.toRoute<GuardianProfileRoute>().guardianId)
                .onSuccess{ data->
                    onAction(GuardianProfileActions.UpdateGuardianData(data))
                    _uiState.value = _uiState.value.copy(screenState = ScreenState.SUCCESS)
                }.onError {
                    _uiState.value = _uiState.value.copy(screenState = ScreenState.ERROR)
                }
        }
    }
    private fun resetButtonState(){
        _uiState.value = _uiState.value.copy(
            toastMessage = null,
            bottomBarState = BottomBarState.IDLE
        )
    }
    private fun updateGuardian(guardian: GuardianFullData){
        _uiState.value = _uiState.value.copy(
            guardianData = guardian
        )
    }
    private fun setErrorMessage(error: NetworkError){
        when(error){
            NetworkError.GUARDIAN_ALREADY_ASSIGNED -> {
                _uiState.value = _uiState.value.copy(
                    toastMessage = UiText.StringResource(
                        R.string.guardian_already_assigned
                    )
                )
            }
            else -> {
                _uiState.value = _uiState.value.copy(
                    toastMessage = UiText.StringResource(
                        R.string.network_error
                    )
                )
            }
        }
    }
    private fun refreshData(){
        viewModelScope.launch{
            updateRefreshState(true)
            guardianProfileUseCase(_uiState.value.guardianId)
                .onSuccess{ result->
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    updateGuardian(result)
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