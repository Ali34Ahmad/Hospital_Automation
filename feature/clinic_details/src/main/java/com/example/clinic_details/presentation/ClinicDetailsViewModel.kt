package com.example.clinic_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.clinic_details.navigation.ClinicDetailsRoute
import com.example.domain.use_cases.doctor.clinic.GetClinicByIdUseCase
import com.example.domain.use_cases.work_request.SendDoctorWorkRequestUseCase
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ui_components.R
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ClinicDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getClinicById: GetClinicByIdUseCase,
    private val sendRequest: SendDoctorWorkRequestUseCase,
): ViewModel() {
    private val refreshTrigger = MutableSharedFlow<Unit>()

    private val _uiState = MutableStateFlow(
        ClinicDetailsUIState(
            clinicId = savedStateHandle.toRoute<ClinicDetailsRoute>().clinicId
        )
    )
    val uiState: StateFlow<ClinicDetailsUIState> = combine(
        _uiState.onStart { getClinicDetails() },
        refreshTrigger.onStart {
            emit(Unit)
        }
    ) { uiState,_-> uiState }
        .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    fun onAction(action: ClinicDetailsUIAction){
        when(action){
            ClinicDetailsUIAction.Refresh -> refresh()
            ClinicDetailsUIAction.SendRequest-> sendRequest
            ClinicDetailsUIAction.HideDialog -> hideDialog()
            is ClinicDetailsUIAction.ShowDialog -> showDialog(action.title,action.subtitle)
            ClinicDetailsUIAction.ClearToast -> clearToast()
        }
    }

    private fun clearToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }

    private fun showDialog(title: String,subtitle: String){
        _uiState.value = _uiState.value.copy(
            isDialogShown = true,
            dialogTitle = title,
            dialogSubtitle = subtitle
        )
    }
    private fun hideDialog(){
        _uiState.value = _uiState.value.copy(isDialogShown = false)
    }
    private fun refresh()=viewModelScope.launch{
        refreshTrigger.emit(Unit)
        refreshData()
    }
    private fun getClinicDetails() = viewModelScope.launch{
        updateScreenState(ScreenState.LOADING)
        val result = getClinicById(uiState.value.clinicId)
        result.onSuccess{ clinic: ClinicFullData->
            updateClinic(clinic)
            updateScreenState(ScreenState.SUCCESS)
            updateSendRequestState(BottomBarState.IDLE)
        }.onError {
            showToast(UiText.StringResource(R.string.something_went_wrong))
            updateScreenState(ScreenState.ERROR)
        }
    }
    private suspend fun refreshData()  {
        updateRefreshing(true)
        val response = getClinicById(uiState.value.clinicId)
        response.onSuccess{clinic: ClinicFullData->
            updateClinic(clinic)
            if(uiState.value.screenState == ScreenState.ERROR){
                updateScreenState(ScreenState.SUCCESS)
            }else{
                showToast(UiText.StringResource(R.string.data_updated_successfully))
            }
        }.onError {
            showToast(UiText.StringResource(R.string.something_went_wrong))
        }
        updateRefreshing(false)
    }
    private fun updateClinic(clinic: ClinicFullData){
        _uiState.value = _uiState.value.copy(clinic = clinic)
    }
    private fun updateRefreshing(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing=isRefreshing)
    }
    private fun updateScreenState(screenState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = screenState)
    }
    private fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateSendRequestState(state: BottomBarState){
        _uiState.value = _uiState.value.copy(sendRequestState = state)
    }
    private fun sendRequest() = viewModelScope.launch{
        updateSendRequestState(BottomBarState.LOADING)
        sendRequest(uiState.value.clinicId).onSuccess{
            updateSendRequestState(BottomBarState.SUCCESS)
        }.onError {
            updateSendRequestState(BottomBarState.IDLE)
            showToast(UiText.StringResource(R.string.something_went_wrong))
        }
    }
}
















