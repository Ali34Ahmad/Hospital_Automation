package com.example.medical_diagnosis.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.doctor.appointment.AddDiagnosisUseCase
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DiagnosisViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addDiagnosisUseCase: AddDiagnosisUseCase
): ViewModel() {
    fun onAction(action: DiagnosisUIAction){
        when(action){
            DiagnosisUIAction.AddDiagnosis -> addDiagnosis()
            is DiagnosisUIAction.UpdateText -> updateText(action.text)
        }
    }
    private val _uiState = MutableStateFlow(DiagnosisUIState())
    val uiState: StateFlow<DiagnosisUIState> = _uiState.onStart {
        initValues()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DiagnosisUIState()
    )

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
    private fun initValues(){
        _uiState.value = _uiState.value.copy(
            appointmentId = 1,
            fullName = "Mira Kamel",
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