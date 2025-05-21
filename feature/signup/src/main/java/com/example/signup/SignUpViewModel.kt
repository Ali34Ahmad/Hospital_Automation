package com.example.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.constants.enums.Gender
import com.example.mappers.toNetworkGender
import com.example.network.constants.Role
import com.example.network.model.request.RegistrationRequest
import com.example.network.model.request.SendOtpRequest
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.utility.network.Error

class SignUpViewModel(
    private val authService: AuthApiService,
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

        override fun onConfirmPasswordChange(confirmPassword: String) {
            updateConfirmPasswordText(confirmPassword)
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
            signUp()
        }
    }

    private fun updateFirstNameText(value: String) {
        _uiState.update {
            it.copy(
                firstName = value
            )
        }
    }

    private fun updateMiddleNameText(value: String) {
        _uiState.update {
            it.copy(
                middleName = value
            )
        }
    }

    private fun updateLastNameText(value: String) {
        _uiState.update {
            it.copy(
                lastName = value
            )
        }
    }

    private fun updateEmailText(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
    }

    private fun updatePhoneNumberText(value: String) {
        _uiState.update {
            it.copy(
                phoneNumber = value
            )
        }
    }

    private fun updatePasswordText(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    private fun updateConfirmPasswordText(value: String) {
        _uiState.update {
            it.copy(
                confirmPassword = value
            )
        }
    }

    private fun updateGender(value: Gender) {
        _uiState.update {
            it.copy(
                gender = value
            )
        }
    }

    private fun updateTermsAndConditionsCheckState(value: Boolean) {
        _uiState.update {
            it.copy(
                isTermsAndConditionsChecked = value
            )
        }
    }

    private fun updatePersonalDataAccessibilityCheckState(value: Boolean) {
        _uiState.update {
            it.copy(
                isPersonalDataAccessibilityChecked = value
            )
        }
    }

    private fun updateIsLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateErrorState(error: Error?) {
        _uiState.update { it.copy(error = error) }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateIsSuccessfulState(isSuccessful: Boolean) {
        _uiState.update { it.copy(isSuccessful = isSuccessful) }
    }

    private fun signUp() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Submitting sign up info","SignUpViewModel")
            val result = authService.signup(
                registrationRequest = RegistrationRequest(
                    firstName = uiState.value.firstName,
                    middleName = uiState.value.middleName,
                    lastName = uiState.value.lastName,
                    email = uiState.value.email,
                    phoneNumber = uiState.value.phoneNumber,
                    password = uiState.value.password,
                    gender = uiState.value.gender?.toNetworkGender() ?: throw Error(""),
                    role = Role.EMPLOYEE.name.lowercase()
                )
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Successful sign up","SignUpViewModel")
                    sendOtpService()
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                    updateIsSuccessfulState(true)
                }

                is Result.Error -> {
                    Log.v("Failed sign up","SignUpViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                    updateIsSuccessfulState(false)
                }
            }
        }
    }

    private suspend fun sendOtpService()=
        authService.sendOtpCodeToEmail(
            sendOtpCodeRequest = SendOtpRequest(
                email = uiState.value.email,
            )
        )
}

