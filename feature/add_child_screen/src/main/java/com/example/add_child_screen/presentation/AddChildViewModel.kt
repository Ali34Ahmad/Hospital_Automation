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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class AddChildViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addChildUseCase: AddChildUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow<AddChildUIState>(AddChildUIState(
        guardianId = savedStateHandle.toRoute<AddChildRoute>().guardianId
    ))

    val uiState: StateFlow<AddChildUIState> = _uiState.onEach {
        validateAll()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    fun onAction(action: AddChildUIActions){
        when(action){
            is AddChildUIActions.OnFirstNameChanged ->{
                _uiState.value = _uiState.value.copy(firstNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(firstName = action.newValue)
                validateFirstName()
            }
            is AddChildUIActions.OnLastNameChanged ->{
                _uiState.value = _uiState.value.copy(lastNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(lastName = action.newValue)
                validateLastName()
            }
            is AddChildUIActions.OnDateChanged -> {
                _uiState.value = _uiState.value.copy(dateOfBirthErrorMessage = null)
                _uiState.value = _uiState.value.copy(dateOfBirth = action.date)
                validateDateOfBirth()
            }
            is AddChildUIActions.OnGenderChanged ->{
                _uiState.value = _uiState.value.copy(gender = action.newGender)
            }
            is AddChildUIActions.OnFatherFirstNameChanged ->{
                _uiState.value = _uiState.value.copy(fatherFirstNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(fatherFirstName = action.newValue)
                validateFatherFirstName()
            }
            is AddChildUIActions.OnFatherLastNameChanged -> {
                _uiState.value = _uiState.value.copy(fatherLastNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(fatherLastName = action.newValue)
                validateFatherLastName()
            }
            is AddChildUIActions.OnMotherFirstNameChanged ->{
                _uiState.value = _uiState.value.copy(motherFirstNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(motherFirstName = action.newValue)
                validateMotherFirstName()
            }
            is AddChildUIActions.OnMotherLastNameChanged ->{
                _uiState.value = _uiState.value.copy(motherLastNameErrorMessage = null)
                _uiState.value = _uiState.value.copy(motherLastName = action.newValue)
                validateMotherLastName()
            }
            is AddChildUIActions.ChangeDatePickerVisibility->{
                _uiState.value = _uiState.value.copy(isDatePickerVisible = action.isVisible)
                validateDateOfBirth()
            }
            AddChildUIActions.SendData ->{
                if(_uiState.value.isValid){
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
                                sendingDataButtonState = BottomBarState.IDLE
                            )
                        }
                    }
                }
                else{
                    showToast(UiText.StringResource(R.string.invalid_values))
                }
            }
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

    /**
     * checks if all values in the form are valid, then it updates the button state
     * from [BottomBarState.DISABLED] to [BottomBarState.IDLE] and make it ready
     * to do its work.
     *
     * @author Ali Mansoura
     */
    fun validateAll(){
        _uiState.value = _uiState.value.copy(isValid = false)
        _uiState.value.run {
            val isValid = listOf(
                firstNameErrorMessage,lastNameErrorMessage,
                fatherFirstNameErrorMessage,fatherLastNameErrorMessage,
                motherFirstNameErrorMessage,motherLastNameErrorMessage,
                dateOfBirthErrorMessage
            ).all{it == null}
            _uiState.value = _uiState.value.copy(isValid = isValid)
            if(isValid) _uiState.value = _uiState.value.copy(sendingDataButtonState = BottomBarState.IDLE)
        }
    }

    fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
}