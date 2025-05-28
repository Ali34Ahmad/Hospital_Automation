package com.example.add_child_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.children.AddChildUseCase
import com.example.model.enums.FetchingDataState
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import com.example.utility.validation.TextValidator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddChildViewModel(
    savedStateHandle: SavedStateHandle,
    private val addChildUseCase: AddChildUseCase
): ViewModel() {
    val guardianId = savedStateHandle.get<Int>("guardianId")

    private var _uiState = MutableStateFlow<AddChildUIState>(AddChildUIState())
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
                   ).any{it == null}
                    _uiState.value = _uiState.value.copy(isValid = isValid)
                }
            }
            is AddChildUIActions.ChangeDatePickerVisibility->{
                _uiState.value = _uiState.value.copy(isDatePickerShown = action.isVisible)
            }
            AddChildUIActions.SendData ->{
                guardianId?.let { id->
                    onAction(AddChildUIActions.Validate)
                    if(_uiState.value.isValid){
                        Log.d("AddChildViewModel",_uiState.value.toString())

                        //sending data using the network call
                        viewModelScope.launch {
                            _uiState.value = _uiState.value.copy(
                                fetchingDataState = FetchingDataState.DOING_PROCESS
                            )
                            addChildUseCase(
                                guardianId = id,
                                child = _uiState.value.toChildFullData()
                            ).onSuccess{
                                _uiState.value = _uiState.value.copy(
                                    fetchingDataState = FetchingDataState.Success
                                )
                            }.onError {
                                _uiState.value = _uiState.value.copy(
                                    fetchingDataState = FetchingDataState.ERROR
                                )
                                delay(1000)
                                onAction(AddChildUIActions.ClearForm)
                            }
                        }

                    }
                }
            }
            AddChildUIActions.ClearForm -> {
                val fetchingDataState = if (_uiState.value.fetchingDataState == FetchingDataState.Success) {
                    FetchingDataState.Success
                } else {
                    FetchingDataState.READY
                }

                _uiState.value = AddChildUIState(fetchingDataState = fetchingDataState )
            }
            AddChildUIActions.NavigateBack -> TODO()
            AddChildUIActions.NavigateToNextScreen -> TODO()
        }
    }

    fun validateFirstName(){
        val error = TextValidator.validate(_uiState.value.firstName)
        error?.let {
            val text = UiText.StringResource(resId = it.string)
            _uiState.value = _uiState.value.copy(firstNameErrorMessage = text)
        }
    }
    fun validateLastName(){
        val error = TextValidator.validate(_uiState.value.lastName)
        error?.let {
            val text = UiText.StringResource(resId = it.string)
            _uiState.value = _uiState.value.copy(lastNameErrorMessage = text)
        }
    }
    fun validateFatherFirstName(){
        val error = TextValidator.validate(_uiState.value.fatherFirstName)
        error?.let {
            val text = UiText.StringResource(resId = it.string)
            _uiState.value = _uiState.value.copy(fatherFirstNameErrorMessage = text)
        }
    }
    fun validateFatherLastName(){
        val error = TextValidator.validate(_uiState.value.fatherLastName)
        error?.let {
            val text = UiText.StringResource(resId = it.string)
            _uiState.value = _uiState.value.copy(fatherLastNameErrorMessage = text)
        }
    }
    fun validateMotherFirstName(){
        val error = TextValidator.validate(_uiState.value.motherFirstName)
        error?.let {
            val text = UiText.StringResource(resId = it.string)
            _uiState.value = _uiState.value.copy(motherLastNameErrorMessage = text)
        }
    }
    fun validateMotherLastName(){
        val error = TextValidator.validate(_uiState.value.motherLastName)
        error?.let {
            val text = UiText.StringResource(resId = it.string)
            _uiState.value = _uiState.value.copy(motherLastNameErrorMessage = text)
        }
    }
    fun validateDateOfBirth(){
        val error = TextValidator.validate(_uiState.value.dateOfBirth)
        error?.let {
            val text = UiText.StringResource(resId = it.string)
            _uiState.value = _uiState.value.copy(dateOfBirthErrorMessage = text)
        }
    }

}
