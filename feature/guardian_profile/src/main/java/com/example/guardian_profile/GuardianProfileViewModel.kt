package com.example.guardian_profile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.constants.FAKE_CHILD_ID
import com.example.data.constants.FAKE_ID
import com.example.domain.use_cases.users.AddGuardianToChildUseCase
import com.example.domain.use_cases.users.GetGuardianByIdUseCase
import com.example.model.enums.FetchingDataState
import com.example.ui_components.R
import com.example.util.UiText
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

//    val guardianId: Int = savedStateHandle.toRoute<GuardianProfileRoute>().guardianId
//    val childId: Int? = savedStateHandle.toRoute<GuardianProfileRoute>().childId
    val childId: Int? = FAKE_CHILD_ID
    val guardianId: Int = FAKE_ID

    private val _uiState = MutableStateFlow(GuardianProfileUIState(hasBottomBar = childId!=null))
    val uiState: StateFlow<GuardianProfileUIState> = _uiState
        .onStart {
            //do initial loading
            loadInitialData()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            GuardianProfileUIState()
        )

    fun onAction(action: GuardianProfileActions){
        when(action){

            GuardianProfileActions.Retry->{
                loadInitialData()
            }
            //network calls
            GuardianProfileActions.SetAsGuardian -> {
                childId?.let {
                    onAction(
                        GuardianProfileActions.UpdateBottomBarState(
                            state = FetchingDataState.DOING_PROCESS
                        )
                    )

                    viewModelScope.launch {
                        addGuardianToChildUseCase(
                            childId = it,
                            userId = guardianId
                        ).onSuccess{
                            onAction(
                                GuardianProfileActions.UpdateBottomBarState(
                                    state = FetchingDataState.Success
                                )
                            )
                        }
                            .onError {
                                onAction(
                                    GuardianProfileActions.UpdateBottomBarState(
                                        state = FetchingDataState.ERROR
                                    )
                                )
                                if(it == NetworkError.GUARDIAN_ALREADY_ASSIGNED){
                                    _uiState.value = _uiState.value.copy(
                                        errorMessage = UiText.StringResource(
                                           R.string.guardian_already_assigned
                                        )
                                    )
                                }
                                delay(3000)
                                onAction(
                                    GuardianProfileActions.UpdateBottomBarState(
                                        state = FetchingDataState.READY
                                    )
                                )
                                _uiState.value = _uiState.value.copy(
                                    errorMessage = null
                                )
                            }
                    }
                }
            }

            // need navigation
            GuardianProfileActions.NavigateBack -> {
                TODO()
            }
            is GuardianProfileActions.Open -> {
                TODO()
            }
            is GuardianProfileActions.OpenEmail -> {
                TODO()
            }
            is GuardianProfileActions.NavigateToChildren -> {
                TODO()
            }

            is GuardianProfileActions.UpdateFetchGuardianState -> {
                _uiState.value = _uiState.value.copy(
                    fetchGuardianState = action.state
                )
            }
            is GuardianProfileActions.UpdateBottomBarState ->{
                _uiState.value = _uiState.value.copy(
                    setAsGuardianState = action.state
                )
            }

            is GuardianProfileActions.UpdateGuardianData ->{
                _uiState.value = _uiState.value.copy(
                    guardianData = action.data
                )
            }
        }
    }

    fun loadInitialData(){
        onAction(
            GuardianProfileActions.UpdateFetchGuardianState(
                state = FetchingDataState.DOING_PROCESS
            )
        )
        viewModelScope.launch {
            Log.d("GuardianProfileViewmodel","Fetching data...")
            guardianProfileUseCase(FAKE_ID)
                .onSuccess{ data->
                    Log.d("GuardianProfileViewmodel","Success Fetching data...")
                    onAction(GuardianProfileActions.UpdateGuardianData(data))
                    onAction(
                        GuardianProfileActions
                            .UpdateFetchGuardianState(state = FetchingDataState.Success)
                    )
                }.onError {
                    Log.e("GuardianProfileViewmodel","Error Fetching data...")
                    onAction(
                        GuardianProfileActions
                            .UpdateFetchGuardianState(state = FetchingDataState.ERROR)
                    )
                }
        }
    }
}