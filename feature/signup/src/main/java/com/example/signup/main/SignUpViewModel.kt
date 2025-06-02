package com.example.signup.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.auth.SendOtpToEmailUseCase
import com.example.domain.use_cases.auth.SignupUseCase
import com.example.model.auth.send_otp.SendOtpRequest
import com.example.model.auth.signup.SignUpCredentials
import com.example.model.enums.ScreenState
import com.example.model.enums.Gender
import com.example.network.constants.Role
import com.example.signup.validator.SignUpValidationResult
import com.example.signup.validator.SignUpValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.utility.network.onError
import com.example.utility.network.onSuccess

class SignUpViewModel(
    private val signUpValidator: SignUpValidator,
    private val signupUseCase: SignupUseCase,
    private val sendOtpToEmailUseCase: SendOtpToEmailUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    fun getUiActions(
        navActions: SignUpNavigationUiActions,
    ): SignUpUiActions = SignUpUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): SignUpBusinessUiActions = object : SignUpBusinessUiActions {
        override fun onFirstNameChange(firstName: String) {
            updateFirstNameText(firstName)
        }

        override fun onMiddleNameChange(middleName: String) {
            updateMiddleNameText(middleName)
        }

        override fun onLastNameChange(lastName: String) {
            updateLastNameText(lastName)
        }

        override fun onEmailChange(email: String) {
            updateEmailText(email)
        }

        override fun onPhoneNumberChange(phoneNumber: String) {
            updatePhoneNumberText(phoneNumber)
        }

        override fun onPasswordChange(password: String) {
            updatePasswordText(password)
        }

        override fun onUpdatePasswordVisibilityChange(isVisible: Boolean) {
            updatePasswordTextVisibility(isVisible)
        }

        override fun onConfirmPasswordChange(confirmPassword: String) {
            updateConfirmPasswordText(confirmPassword)
        }

        override fun onUpdateConfirmPasswordVisibilityChange(isVisible: Boolean) {
            updateConfirmPasswordTextVisibility(isVisible)
        }

        override fun onGenderChange(gender: Gender) {
            updateGender(gender)
        }

        override fun onTermsAndConditionsCheckChange(value: Boolean) {
            updateTermsAndConditionsCheckState(value)
        }

        override fun onPersonalDataAccessibilityCheckChange(value: Boolean) {
            updatePersonalDataAccessibilityCheckState(value)
        }

        override fun onShowErrorDialogStateChange(value: Boolean) {
            updateShowErrorDialogState(value)
        }


        override fun onSignUpButtonClick() {
            validateAndSignUp()
        }
    }

    private fun updateFirstNameText(value: String) {
        _uiState.update {
            it.copy(
                firstName = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateMiddleNameText(value: String) {
        _uiState.update {
            it.copy(
                middleName = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateLastNameText(value: String) {
        _uiState.update {
            it.copy(
                lastName = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateEmailText(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updatePhoneNumberText(value: String) {
        _uiState.update {
            it.copy(
                phoneNumber = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updatePasswordText(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updatePasswordTextVisibility(isVisible: Boolean) {
        _uiState.update {
            it.copy(
                showPasswordText = isVisible
            )
        }
    }

    private fun updateConfirmPasswordText(value: String) {
        _uiState.update {
            it.copy(
                confirmPassword = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateConfirmPasswordTextVisibility(isVisible: Boolean) {
        _uiState.update {
            it.copy(
                showConfirmPasswordText = isVisible
            )
        }
    }

    private fun updateGender(value: Gender) {
        _uiState.update {
            it.copy(
                gender = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateTermsAndConditionsCheckState(value: Boolean) {
        _uiState.update {
            it.copy(
                isTermsAndConditionsChecked = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updatePersonalDataAccessibilityCheckState(value: Boolean) {
        _uiState.update {
            it.copy(
                isPersonalDataAccessibilityChecked = value
            )
        }
        updateSignUpButtonEnablement()
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateScreenState(screenState: ScreenState){
        _uiState.update {
            it.copy(
                screenState = screenState
            )
        }
    }

    private fun updateSignUpButtonEnablement() {
        _uiState.update { currentState ->
            val allFieldsFilled = currentState.firstName.isNotEmpty() &&
                    currentState.middleName.isNotEmpty() &&
                    currentState.lastName.isNotEmpty() &&
                    currentState.email.isNotEmpty() &&
                    currentState.phoneNumber.isNotEmpty() &&
                    currentState.password.isNotEmpty() &&
                    currentState.confirmPassword.isNotEmpty()

            val genderSelected = currentState.gender != null
            val checkBoxesChecked = currentState.isTermsAndConditionsChecked &&
                    currentState.isPersonalDataAccessibilityChecked

            currentState.copy(isSignUpButtonEnabled = allFieldsFilled && genderSelected && checkBoxesChecked)
        }
    }

    private fun updateValidationErrors(result: SignUpValidationResult) {
        _uiState.update {
            it.copy(
                firstNameError = result.firstNameError,
                middleNameError = result.middleNameError,
                lastNameError = result.lastNameError,
                emailError = result.emailError,
                phoneNumberError = result.phoneNumberError,
                passwordError = result.passwordError,
                confirmPasswordError = result.confirmPasswordError
            )
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Submitting sign up info", "SignUpViewModel")
            signupUseCase(
                registrationRequest = SignUpCredentials(
                    firstName = uiState.value.firstName.trim(),
                    middleName = uiState.value.middleName.trim(),
                    lastName = uiState.value.lastName.trim(),
                    email = uiState.value.email.trim(),
                    phoneNumber = uiState.value.phoneNumber.trim(),
                    password = uiState.value.password.trim(),
                    gender = uiState.value.gender ?: Gender.MALE,
                    role = Role.EMPLOYEE.name.lowercase().trim()
                )
            ).onSuccess { response ->
                Log.v("Successful sign up", "SignUpViewModel")
                sendOtpService()
                updateScreenState(ScreenState.Success)
                updateShowErrorDialogState(false)
            }.onError { error ->
                Log.v("Failed sign up", "SignUpViewModel")
                updateScreenState(ScreenState.ERROR)
                updateShowErrorDialogState(true)
            }
        }
    }

    private fun validateAndSignUp(){
        val validationResult = signUpValidator.validate(uiState.value)
        updateValidationErrors(validationResult)
        if (!validationResult.hasErrors()) {
            signUp()
        }
    }

    private suspend fun sendOtpService() =
        sendOtpToEmailUseCase(
            sendOtpCodeRequest = SendOtpRequest(
                email = uiState.value.email.trim(),
            )
        )
}