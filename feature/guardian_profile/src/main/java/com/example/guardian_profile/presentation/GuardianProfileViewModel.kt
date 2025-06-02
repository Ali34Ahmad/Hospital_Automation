package com.example.guardian_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.data.constants.FAKE_ID
import com.example.domain.use_cases.users.AddGuardianToChildUseCase
import com.example.domain.use_cases.users.GetGuardianByIdUseCase
import com.example.guardian_profile.navigation.GuardianProfileRoute
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
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
        guardianId = savedStateHandle.toRoute<GuardianProfileRoute>().guardianId
        )
    )

    val uiState: StateFlow<GuardianProfileUIState> = _uiState
        .onStart {
            loadInitialData()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            GuardianProfileUIState()
        )

    fun onAction(action: GuardianProfileActions){
        when(action){

            GuardianProfileActions.RetryLoadingData->{
                loadInitialData()
            }
            //network call to add guardian to child
            GuardianProfileActions.SetAsGuardian -> {
                _uiState.value.childId?.let {
                    onAction(
                        GuardianProfileActions.UpdateBottomBarState(
                            state = BottomBarState.LOADING
                        )
                    )
                    viewModelScope.launch {
                        addGuardianToChildUseCase(
                            childId = it,
                            userId = _uiState.value.guardianId
                        ).onSuccess{
                            onAction(
                                GuardianProfileActions.UpdateBottomBarState(
                                    state = BottomBarState.SUCCESS
                                )
                            )
                        }
                            .onError {error->
                                onAction(
                                    GuardianProfileActions.UpdateBottomBarState(
                                        state = BottomBarState.FAILURE
                                    )
                                )
                                setErrorMessage(error)
                                delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
                                resetButtonState()
                            }
                    }
                }
            }

            is GuardianProfileActions.UpdateScreenState -> {
                _uiState.value = _uiState.value.copy(
                    screenState = action.state
                )
            }
            is GuardianProfileActions.UpdateBottomBarState ->{
                _uiState.value = _uiState.value.copy(
                    bottomBarState = action.state
                )
            }
            is GuardianProfileActions.UpdateGuardianData ->{
                _uiState.value = _uiState.value.copy(
                    guardianData = action.data
                )
            }
            // need navigation
            GuardianProfileActions.NavigateBack -> Unit
            is GuardianProfileActions.Open -> Unit
            is GuardianProfileActions.OpenEmail -> Unit
            is GuardianProfileActions.NavigateToChildren -> Unit

        }
    }

    private fun loadInitialData(){
        onAction(
            GuardianProfileActions.UpdateScreenState(
                state = ScreenState.LOADING
            )
        )
        viewModelScope.launch {
            guardianProfileUseCase(FAKE_ID)
                .onSuccess{ data->
                    onAction(GuardianProfileActions.UpdateGuardianData(data))
                    onAction(
                        GuardianProfileActions
                            .UpdateScreenState(state = ScreenState.Success)
                    )
                }.onError {
                    onAction(
                        GuardianProfileActions
                            .UpdateScreenState(state = ScreenState.ERROR)
                    )
                }
        }
    }
    private fun resetButtonState(){
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            bottomBarState = BottomBarState.IDLE
        )
    }
    private fun setErrorMessage(error: NetworkError){
        when(error){
            NetworkError.GUARDIAN_ALREADY_ASSIGNED -> {
                _uiState.value = _uiState.value.copy(
                    errorMessage = UiText.StringResource(
                        R.string.guardian_already_assigned
                    )
                )
            }
            else -> {
                _uiState.value = _uiState.value.copy(
                    errorMessage = UiText.StringResource(
                        R.string.network_error
                    )
                )
            }
        }
    }
}