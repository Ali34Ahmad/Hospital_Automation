package com.example.child_profile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.children.GetChildByIdUseCase
import com.example.model.Child
import com.example.model.child.ChildFullData
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

const val fakeId = 1
class ChildProfileViewModel (
    private val getChildByIdUseCase: GetChildByIdUseCase,
//    savedStateHandle: SavedStateHandle
): ViewModel() {

    val childId = fakeId

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
    fun loadData() {
        viewModelScope.launch{
            Log.d("ChildViewModel","Start")
            onAction(ChildProfileUIAction.UpdateLoadingState(true))
            //loading the data
            getChildByIdUseCase(childId)
                .onSuccess{ result->
                    Log.d("ChildViewModel","Success")
                    onAction(ChildProfileUIAction.UpdateChild(result))
                }.onError {
                    //need some logic for message
                    Log.d("ChildViewModel","Error")
                    onAction(ChildProfileUIAction.ShowError)
                }
            Log.d("ChildViewModel","finish")
            onAction(ChildProfileUIAction.UpdateLoadingState(false))
        }
    }

    fun onAction(action: ChildProfileUIAction){
        when(action){
            ChildProfileUIAction.HideError -> {
                _uiState.value = _uiState.value.copy(
                    hasError = false
                )
            }
            ChildProfileUIAction.ShowError ->{
                _uiState.value = _uiState.value.copy(
                    hasError = true
                )
            }
            is ChildProfileUIAction.UpdateLoadingState -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = action.isLoading
                )
            }
            is ChildProfileUIAction.UpdateChild ->{
                _uiState.value = _uiState.value.copy(child = action.child)
            }

            is ChildProfileUIAction.NavigateToAddGuardianScreen -> TODO()
            is ChildProfileUIAction.NavigateToBirthCertificateScreen -> TODO()
            is ChildProfileUIAction.NavigateToEmployeeProfileScreen -> TODO()
            is ChildProfileUIAction.NavigateToGuardiansScreen -> TODO()
            ChildProfileUIAction.NavigateUp -> TODO()
        }
    }

}

