package com.example.login.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.auth.LoginUseCase
import com.example.login.validator.LoginValidationResult
import com.example.login.validator.LoginValidator
import com.example.model.auth.login.LoginRequest
import com.example.model.enums.ScreenState
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginValidator: LoginValidator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun getUiActions(
        navActions: LoginNavigationUiActions,
    ): LoginUiActions = LoginUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): LoginBusinessUiActions = object : LoginBusinessUiActions {
        override fun onEmailChange(email: String) {
            updateEmailText(email)
        }

        override fun onPasswordChange(password: String) {
            updatePasswordText(password)
        }

        override fun onUpdatePasswordVisibility(isVisible: Boolean) {
            updatePasswordVisibility(isVisible)
        }

        override fun onShowErrorDialogStateChange(value: Boolean) {
            updateShowErrorDialogState(value)
        }

        override fun onLoginButtonClick() {
            validateAndLogin()
        }
    }

    private fun updateEmailText(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
        updateLoginButtonEnablement()
    }

    private fun updatePasswordText(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
        updateLoginButtonEnablement()
    }

    private fun updatePasswordVisibility(isVisible: Boolean) {
        _uiState.update {
            it.copy(
                showPassword = isVisible
            )
        }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateLoginButtonEnablement() {
        _uiState.update { currentState ->
            val allFieldsFilled = currentState.email.isNotEmpty() &&
                    currentState.password.isNotEmpty()

            currentState.copy(isLoginButtonEnabled = allFieldsFilled)
        }
    }

    private fun updateValidationErrors(result: LoginValidationResult) {
        _uiState.update {
            it.copy(
                emailError = result.emailError,
                passwordError = result.passwordError,
            )
        }
    }

    private fun updateScreenState(screenState: ScreenState){
        _uiState.update {
            it.copy(
                screenState=screenState
            )
        }
    }

    private fun login() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Submitting log in info","LoginViewModel")
            loginUseCase(
                loginRequest = LoginRequest(
                    email = uiState.value.email,
                    password = uiState.value.password,
                )
            ).onSuccess{
                Log.v("Successful log in","LoginViewModel")
                updateScreenState(ScreenState.Success)
                updateShowErrorDialogState(false)
            }.onError {error->
                Log.v("Failed log in","LoginViewModel")
                updateScreenState(ScreenState.ERROR)
                updateShowErrorDialogState(true)
            }
        }
    }

    private fun validateAndLogin(){
        val validationResult = loginValidator.validate(uiState.value)
        updateValidationErrors(validationResult)
        if (!validationResult.hasErrors()) {
            login()
        }
    }
}

