package com.example.medical_diagnosis.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.appointment.AddDiagnosisUseCase
import com.example.medical_diagnosis.navigation.DiagnosisRoute
import com.example.model.enums.BottomBarState
import com.example.util.UiText
import com.example.utility.validation.validator.TextValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.ui_components.R
import com.example.utility.constants.DurationConstants
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DiagnosisViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addDiagnosisUseCase: AddDiagnosisUseCase
): ViewModel() {
    private val route = savedStateHandle.toRoute<DiagnosisRoute>()
    fun onAction(action: DiagnosisUIAction){
        when(action){
            DiagnosisUIAction.AddDiagnosis -> addDiagnosis()
            is DiagnosisUIAction.UpdateText -> updateText(action.text)
            DiagnosisUIAction.ClearToast -> {
                _uiState.value = _uiState.value.copy(toastMessage = null)
            }
        }
    }
    private val _uiState = MutableStateFlow(DiagnosisUIState(
        appointmentId = route.appointmentId,
        fullName = route.fullName,
        patientId = route.patientId,
        childId = route.childId,
        canSkip = route.canSkip
    ))

    val uiState: StateFlow<DiagnosisUIState> = _uiState

    private fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateText(text: String){
        _uiState.value = _uiState.value.copy(text = text)
        validate()
    }
    private fun updateBottomBarState(newState: BottomBarState){
        _uiState.value = _uiState.value.copy(
            sendDateState = newState
        )
    }
    private fun clearErrorMessage(){
        _uiState.value = _uiState.value.copy(textFieldErrorText = null)
    }
    private fun addDiagnosis()= viewModelScope.launch{
        updateBottomBarState(BottomBarState.LOADING)
        addDiagnosisUseCase(
            appointmentId = uiState.value.appointmentId,
            diagnosis = uiState.value.text
        ).onSuccess{
            updateBottomBarState(BottomBarState.SUCCESS)
            showToast(UiText.StringResource(R.string.success))
        }.onError {
            showToast(UiText.StringResource(R.string.something_went_wrong))
            updateBottomBarState(BottomBarState.FAILURE)
            delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
            updateBottomBarState(BottomBarState.IDLE)
        }
    }
    private fun validate(){
        clearErrorMessage()
        val text = uiState.value.text
        val error = TextValidator.validate(text)
        if(error != null){
            _uiState.value = _uiState.value.copy(
                isValid = false,
                sendDateState = BottomBarState.DISABLED,
                textFieldErrorText = UiText.StringResource(R.string.empty_field)
            )
        }else{
            _uiState.value = _uiState.value.copy(sendDateState = BottomBarState.IDLE)
        }
    }
}