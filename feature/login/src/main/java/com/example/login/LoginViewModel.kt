package com.example.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.request.LoginRequest
import com.example.network.model.request.SendOtpRequest
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthApiService,
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

        override fun onShowErrorDialogStateChange(value: Boolean) {
            updateShowErrorDialogState(value)
        }

        override fun onLoginButtonClick() {
            login()
        }
    }

    private fun updateEmailText(value: String) {
        _uiState.update {
            it.copy(
                email = value
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

    private fun updateIsLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateErrorState(error: Error?) {
        _uiState.update { it.copy(error = error) }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateIsSuccessfulLoginState(isSuccessful: Boolean) {
        _uiState.update { it.copy(isSuccessfulLogin = isSuccessful) }
    }

    private fun login() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Submitting log in info","LoginViewModel")
            val result = authService.login(
                loginRequest = LoginRequest(
                    email = uiState.value.email,
                    password = uiState.value.password,
                )
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Successful log in","LoginViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                    updateIsSuccessfulLoginState(true)
                }

                is Result.Error -> {
                    Log.v("Failed log in","LoginViewModel")
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(result.error)
                    updateIsSuccessfulLoginState(false)
                }
            }
        }
    }
}

