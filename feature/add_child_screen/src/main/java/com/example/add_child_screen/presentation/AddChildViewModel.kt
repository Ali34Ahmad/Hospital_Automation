package com.example.add_child_screen.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.add_child_screen.navigation.AddChildRoute
import com.example.domain.use_cases.children.AddChildUseCase
import com.example.model.child.ChildFullData
import com.example.model.enums.BottomBarState
import com.example.util.UiText
import com.example.utility.constants.DurationConstants
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import com.example.utility.validation.validator.TextValidator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ui_components.R

class AddChildViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addChildUseCase: AddChildUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow<AddChildUIState>(AddChildUIState(
        guardianId = savedStateHandle.toRoute<AddChildRoute>().guardianId
    ))
    val uiState: StateFlow<AddChildUIState> = _uiState

    fun onAction(action: AddChildUIActions){
        when(action){
            is AddChildUIActions.OnFirstNameChanged ->{
                _uiState.value = _uiState.value.copy(firstNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(firstName = action.newValue)
            }
            is AddChildUIActions.OnLastNameChanged ->{
                _uiState.value = _uiState.value.copy(lastNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(lastName = action.newValue)
            }
            is AddChildUIActions.OnDateChanged -> {
                _uiState.value = _uiState.value.copy(dateOfBirthErrorMessage = null)
                _uiState.value = _uiState.value.copy(dateOfBirth = action.date)
            }
            is AddChildUIActions.OnGenderChanged ->{
                _uiState.value = _uiState.value.copy(gender = action.newGender)
            }
            is AddChildUIActions.OnFatherFirstNameChanged ->{
                _uiState.value = _uiState.value.copy(fatherFirstNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(fatherFirstName = action.newValue)
            }
            is AddChildUIActions.OnFatherLastNameChanged -> {
                _uiState.value = _uiState.value.copy(fatherLastNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(fatherLastName = action.newValue)
            }
            is AddChildUIActions.OnMotherFirstNameChanged ->{
                _uiState.value = _uiState.value.copy(motherFirstNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(motherFirstName = action.newValue)
            }
            is AddChildUIActions.OnMotherLastNameChanged ->{
                _uiState.value = _uiState.value.copy(motherLastNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(motherLastName = action.newValue)
            }
            AddChildUIActions.Validate ->{
                _uiState.value = _uiState.value.copy(isValid = false)
                validateFirstName()
                validateLastName()
                validateDateOfBirth()
                validateFatherFirstName()
                validateFatherLastName()
                validateMotherFirstName()
                validateMotherLastName()
                _uiState.value.apply {
                   val isValid = listOf(
                       firstNameErrorMessage,lastNameErrorMessage,dateOfBirthErrorMessage,
                       fatherFirstNameErrorMessage,fatherFirstNameErrorMessage,
                       motherFirstNameErrorMessage,motherLastNameErrorMessage
                   ).all{it == null}
                    _uiState.value = _uiState.value.copy(isValid = isValid)
                }
            }
            is AddChildUIActions.ChangeDatePickerVisibility->{
                _uiState.value = _uiState.value.copy(isDatePickerVisible = action.isVisible)
            }
            AddChildUIActions.SendData ->{

                    onAction(AddChildUIActions.Validate)
                    if(_uiState.value.isValid){
                        //sending data using the network call
                        viewModelScope.launch {
                            _uiState.value = _uiState.value.copy(
                                sendingDataButtonState = BottomBarState.LOADING
                            )
                            addChildUseCase(
                                guardianId = _uiState.value.guardianId,
                                child = _uiState.value.toChildFullData()
                            ).onSuccess{child: ChildFullData->
                                _uiState.value = _uiState.value.copy(
                                    sendingDataButtonState = BottomBarState.SUCCESS,
                                    childId = child.childId
                                )
                                delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
                                _uiState.value = _uiState.value.copy(isSendingDataButtonVisible = false)
                            }.onError {
                                _uiState.value = _uiState.value.copy(
                                    sendingDataButtonState = BottomBarState.FAILURE
                                )
                                delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
                                onAction(AddChildUIActions.ClearForm)
                            }
                        }


                }
            }
            AddChildUIActions.ClearForm -> {
                val bottomBarState = if (_uiState.value.sendingDataButtonState == BottomBarState.SUCCESS) {
                    BottomBarState.SUCCESS
                } else {
                    BottomBarState.IDLE
                }
                _uiState.value = AddChildUIState(sendingDataButtonState = bottomBarState )
            }
            AddChildUIActions.NavigateBack -> Unit
            AddChildUIActions.NavigateToNextScreen -> Unit
        }
    }

    fun validateFirstName(){
        val error = TextValidator.validate(_uiState.value.firstName)
        error?.let {
            val text = UiText.StringResource(resId = R.string.required_field)
            _uiState.value = _uiState.value.copy(firstNameErrorMessage = text)
        }
    }
    fun validateLastName(){
        val error = TextValidator.validate(_uiState.value.lastName)
        error?.let {
            val text = UiText.StringResource(resId = R.string.required_field)
            _uiState.value = _uiState.value.copy(lastNameErrorMessage = text)
        }
    }
    fun validateFatherFirstName(){
        val error = TextValidator.validate(_uiState.value.fatherFirstName)
        error?.let {
            val text = UiText.StringResource(resId = R.string.required_field)
            _uiState.value = _uiState.value.copy(fatherFirstNameErrorMessage = text)
        }
    }
    fun validateFatherLastName(){
        val error = TextValidator.validate(_uiState.value.fatherLastName)
        error?.let {
            val text = UiText.StringResource(resId = R.string.required_field)
            _uiState.value = _uiState.value.copy(fatherLastNameErrorMessage = text)
        }
    }
    fun validateMotherFirstName(){
        val error = TextValidator.validate(_uiState.value.motherFirstName)
        error?.let {
            val text = UiText.StringResource(resId = R.string.required_field)
            _uiState.value = _uiState.value.copy(motherLastNameErrorMessage = text)
        }
    }
    fun validateMotherLastName(){
        val error = TextValidator.validate(_uiState.value.motherLastName)
        error?.let {
            val text = UiText.StringResource(resId = R.string.required_field)
            _uiState.value = _uiState.value.copy(motherLastNameErrorMessage = text)
        }
    }
    fun validateDateOfBirth(){
        val error = TextValidator.validate(_uiState.value.dateOfBirth)
        error?.let {
            val text = UiText.StringResource(resId = R.string.required_field)
            _uiState.value = _uiState.value.copy(dateOfBirthErrorMessage = text)
        }
    }

}
