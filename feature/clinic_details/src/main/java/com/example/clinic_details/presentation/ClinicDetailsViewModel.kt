package com.example.clinic_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.clinic_details.navigation.ClinicDetailsRoute
import com.example.clinic_details.navigation.ClinicDetailsType
import com.example.domain.use_cases.admin.clinic.DeactivateClinicUseCase
import com.example.domain.use_cases.admin.clinic.ReactivateClinicUseCase
import com.example.domain.use_cases.doctor.clinic.GetClinicByIdUseCase
import com.example.domain.use_cases.validator.ValidateTextUseCase
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
    private val deactivateClinicUseCase: DeactivateClinicUseCase,
    private val reactivateClinicUseCase: ReactivateClinicUseCase,
    private val validateTextUseCase: ValidateTextUseCase
): ViewModel() {

    private val route = savedStateHandle.toRoute<ClinicDetailsRoute>()

    private val refreshTrigger = MutableSharedFlow<Unit>()

    private val _uiState = MutableStateFlow(
        ClinicDetailsUIState(
            clinicId = route.clinicId,
            type = route.type
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
            ClinicDetailsUIAction.SendRequest-> sendDoctorRequest()
            ClinicDetailsUIAction.HideInfoDialog -> hideInfoDialog()
            is ClinicDetailsUIAction.ShowInfoDialog -> showInfoDialog(action.title,action.subtitle)
            ClinicDetailsUIAction.ClearToast -> clearToast()
            ClinicDetailsUIAction.ShowWarningDialog -> showWarningDialog()
            ClinicDetailsUIAction.HideWarningDialog -> hideWarningDialog()
            ClinicDetailsUIAction.ShowLoadingDialog -> showLoadingDialog()
            ClinicDetailsUIAction.HideLoadingDialog -> hideLoadingDialog()
            is ClinicDetailsUIAction.UpdateDeactivationReason -> updateDeactivationReason(action.newValue)
            ClinicDetailsUIAction.DeactivateClinic -> deactivateClinic()
            ClinicDetailsUIAction.ReactivateClinic -> reactivateClinic()
            ClinicDetailsUIAction.ClearDeactivationReason -> clearDeactivationReason()
        }
    }
    private fun validateInput(){
        val textError = validateTextUseCase(uiState.value.deactivationReason)
        val hasError = textError != null
        updateIsValidInput(!hasError)
    }
    private fun updateIsValidInput(newValue: Boolean?){
        _uiState.value = _uiState.value.copy(isValidInput = newValue)
    }
    private fun reactivateClinic() = viewModelScope.launch{
        showLoadingDialog()
        reactivateClinicUseCase(
            clinicId = uiState.value.clinicId
        ).onError{
            showToast(UiText.StringResource(R.string.something_went_wrong))
        }.onSuccess {
            showToast(UiText.StringResource(R.string.success))
            refreshData()
        }
        hideLoadingDialog()
    }
    private fun deactivateClinic()= viewModelScope.launch{
        showLoadingDialog()
        deactivateClinicUseCase(
            clinicId = uiState.value.clinicId,
            deactivationReason = uiState.value.deactivationReason
        ).onError{
            showToast(UiText.StringResource(R.string.something_went_wrong))
        }.onSuccess {
            refreshData()
            showToast(UiText.StringResource(R.string.deactivate_account_message))
        }
        clearDeactivationReason()
        hideLoadingDialog()

    }
    private fun clearDeactivationReason(){
        _uiState.value = _uiState.value.copy(deactivationReason = "")
    }
    private fun clearToast() {
        _uiState.value = _uiState.value.copy(toastMessage = null)
    }

    private fun showInfoDialog(title: String, subtitle: String){
        _uiState.value = _uiState.value.copy(
            isInfoDialogShown = true,
            infoDialogTitle = title,
            infoDialogSubtitle = subtitle
        )
    }
    private fun hideLoadingDialog(){
        _uiState.value = _uiState.value.copy(isLoadingDialogShown = false)
    }
    private fun showLoadingDialog(){
        _uiState.value = _uiState.value.copy(isLoadingDialogShown = true)
    }
    private fun showWarningDialog(){
        updateDeactivationReason("")
        updateIsValidInput(null)
        _uiState.value = _uiState.value.copy(isWarningDialogShown = true)
    }
    private fun hideWarningDialog(){
        _uiState.value = _uiState.value.copy(isWarningDialogShown = false)
    }
    private fun updateDeactivationReason(newValue: String){
        _uiState.value = _uiState.value.copy(deactivationReason = newValue)
        validateInput()
    }

    private fun hideInfoDialog(){
        _uiState.value = _uiState.value.copy(isInfoDialogShown = false)
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
    private fun sendDoctorRequest() = viewModelScope.launch{
        updateSendRequestState(BottomBarState.LOADING)
        sendRequest(uiState.value.clinicId).onSuccess{
            updateSendRequestState(BottomBarState.SUCCESS)
        }.onError {
            updateSendRequestState(BottomBarState.IDLE)
            showToast(UiText.StringResource(R.string.something_went_wrong))
        }
    }

}
















