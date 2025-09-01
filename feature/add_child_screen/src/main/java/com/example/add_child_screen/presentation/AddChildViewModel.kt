package com.example.add_child_screen.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.add_child_screen.navigation.AddChildRoute
import com.example.domain.use_cases.children.AddChildUseCase
import com.example.ext.toAppropriateDateFormat
import com.example.model.child.ChildFullData
import com.example.model.enums.BottomBarState
import com.example.model.enums.Gender
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import com.example.utility.validation.validator.TextValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ui_components.R
import java.time.LocalDate

class AddChildViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addChildUseCase: AddChildUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow<AddChildUIState>(AddChildUIState(
        guardianId = savedStateHandle.toRoute<AddChildRoute>().guardianId
    ))

    val uiState: StateFlow<AddChildUIState> = _uiState

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(action: AddChildUIActions){
        when(action){
            is AddChildUIActions.OnFirstNameChanged ->{
                updateFirstName(action.newValue)
            }
            is AddChildUIActions.OnLastNameChanged ->{
                updateLastName(action.newValue)
            }
            is AddChildUIActions.OnDateChanged -> {
                updateDateOfBirth(action.date)
            }
            is AddChildUIActions.OnGenderChanged ->{
               updateGender(action.newGender)
            }
            is AddChildUIActions.OnFatherFirstNameChanged ->{
                updateFatherFirstName(action.newValue)
            }
            is AddChildUIActions.OnFatherLastNameChanged -> {
                updateFatherLastName(action.newValue)
            }
            is AddChildUIActions.OnMotherFirstNameChanged ->{
                updateMotherFirstName(action.newValue)
            }
            is AddChildUIActions.OnMotherLastNameChanged ->{
                updateMotherLastName(action.newValue)
            }
            is AddChildUIActions.ChangeDatePickerVisibility->{
                _uiState.value = _uiState.value.copy(isDatePickerVisible = action.isVisible)
            }
            AddChildUIActions.SendData ->{
                validateAll()
                if(_uiState.value.isValid){
                    viewModelScope.launch {
                        updateBottomBarButtonState(BottomBarState.LOADING)
                        addChildUseCase(
                            guardianId = _uiState.value.guardianId,
                            child = _uiState.value.toChildFullData()
                        ).onSuccess{child: ChildFullData->
                            showToast(UiText.StringResource(R.string.success))

                            updateBottomBarButtonState(BottomBarState.SUCCESS)

                            updateChild(childId = child.childId)
                        }.onError {
                            showToast(UiText.StringResource(R.string.something_went_wrong))

                            updateBottomBarButtonState(BottomBarState.IDLE)
                        }
                    }
                }
                else{
                    showToast(UiText.StringResource(R.string.invalid_values))
                }
            }
        }
    }
    private fun updateChild(childId: Int?){
        _uiState.value = _uiState.value.copy(childId = childId)
    }
    private fun validateFirstName(){
        val error = TextValidator.validate(_uiState.value.firstName)
        val text = error?.let { UiText.StringResource(resId = R.string.required_field) }
        _uiState.value = _uiState.value.copy(firstNameErrorMessage = text)
    }
    private fun validateLastName(){
        val error = TextValidator.validate(_uiState.value.lastName)
        val text = error?.let { UiText.StringResource(resId = R.string.required_field) }
        _uiState.value = _uiState.value.copy(lastNameErrorMessage = text)
    }
    private fun validateFatherFirstName(){
        val error = TextValidator.validate(_uiState.value.fatherFirstName)
        val text = error?.let { UiText.StringResource(resId = R.string.required_field) }
        _uiState.value = _uiState.value.copy(fatherFirstNameErrorMessage = text)
    }
    private fun validateFatherLastName(){
        val error = TextValidator.validate(_uiState.value.fatherLastName)
        val text = error?.let { UiText.StringResource(resId = R.string.required_field) }
        _uiState.value = _uiState.value.copy(fatherLastNameErrorMessage = text)
    }
    private fun validateMotherFirstName(){
        val error = TextValidator.validate(_uiState.value.motherFirstName)
        val text = error?.let { UiText.StringResource(resId = R.string.required_field) }
        _uiState.value = _uiState.value.copy(motherFirstNameErrorMessage = text)
    }
    private fun validateMotherLastName(){
        val error = TextValidator.validate(_uiState.value.motherLastName)
        val text = error?.let { UiText.StringResource(resId = R.string.required_field) }
        _uiState.value = _uiState.value.copy(motherLastNameErrorMessage = text)
    }
    private fun validateDateOfBirth(){
        val error = TextValidator.validate(_uiState.value.dateOfBirth?.toAppropriateDateFormat().orEmpty())
        val text = error?.let { UiText.StringResource(resId = R.string.required_field) }
        _uiState.value = _uiState.value.copy(dateOfBirthErrorMessage = text)
    }

    private fun validateAll(){
        validateFirstName()
        validateLastName()
        validateDateOfBirth()
        validateFatherFirstName()
        validateFatherLastName()
        validateMotherFirstName()
        validateMotherLastName()

        val isValid = _uiState.value.errorMessages.all{it == null}
        _uiState.value = _uiState.value.copy(isValid = isValid)

    }

    private fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun enableBottomBarButtonIfNeeded(){
        val textFieldsInputs = _uiState.value.textFieldsStringInputs
        val newState = if (textFieldsInputs.all { it.isNotBlank() }) BottomBarState.IDLE
        else BottomBarState.DISABLED

        updateBottomBarButtonState(newState)
    }
    private fun updateBottomBarButtonState(state: BottomBarState){
        _uiState.value = _uiState.value.copy(sendingDataButtonState= state)
    }
    private fun updateFirstName(value: String){
        _uiState.value = _uiState.value.copy(firstName = value)
        enableBottomBarButtonIfNeeded()
    }
    private fun updateLastName(value: String){
        _uiState.value = _uiState.value.copy(lastName = value)
        enableBottomBarButtonIfNeeded()
    }
    private fun updateFatherFirstName(value: String){
        _uiState.value = _uiState.value.copy(fatherFirstName = value)
        enableBottomBarButtonIfNeeded()
    }
    private fun updateFatherLastName(value: String){
        _uiState.value = _uiState.value.copy(fatherLastName = value)
        enableBottomBarButtonIfNeeded()
    }
    private fun updateMotherFirstName(value: String){
        _uiState.value = _uiState.value.copy(motherFirstName = value)
        enableBottomBarButtonIfNeeded()
    }
    private fun updateMotherLastName(value: String){
        _uiState.value = _uiState.value.copy(motherLastName = value)
        enableBottomBarButtonIfNeeded()
    }
    private fun updateDateOfBirth(value: LocalDate){
        _uiState.value = _uiState.value.copy(dateOfBirth = value)
        enableBottomBarButtonIfNeeded()
    }
    private fun updateGender(value: Gender){
        _uiState.value = _uiState.value.copy(gender = value)
    }


}