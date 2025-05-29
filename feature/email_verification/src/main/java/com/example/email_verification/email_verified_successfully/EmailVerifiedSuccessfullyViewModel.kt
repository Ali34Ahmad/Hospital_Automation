package com.example.email_verification.email_verified_successfully

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repositories.AuthRepository
import com.example.domain.use_cases.auth.LoginUseCase
import com.example.domain.use_cases.auth.VerifyEmailUseCase
import com.example.email_verification.EmailVerificationNavConstants
import com.example.model.auth.login.LoginRequest
import com.example.network.model.request.LoginRequestDto
import com.example.network.remote.auth.AuthApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmailVerifiedSuccessfullyViewModel(
    private val loginUseCase: LoginUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmailVerifiedSuccessfullyUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                email = savedStateHandle.get<String>(EmailVerificationNavConstants.EMAIL) ?: "",
                password = savedStateHandle.get<String>(EmailVerificationNavConstants.PASSWORD) ?: "",
                navigateToResetPassword = savedStateHandle.get<Boolean>(EmailVerificationNavConstants.NAVIGATE_TO_RESET_PASSWORD) ?: false,
            )
        }
    }

    fun getUiActions(
        navActions: EmailVerifiedSuccessfullyNavigationUiActions,
    ): EmailVerifiedSuccessfullyUiActions = EmailVerifiedSuccessfullyUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): EmailVerifiedSuccessfullyBusinessUiActions =
        object : EmailVerifiedSuccessfullyBusinessUiActions {

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onNextButtonClick() {
                login()
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

    private fun login() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Submitting log in info", "EmailVerifiedSuccessfullyViewModel")
            val result = loginUseCase(
                loginRequest = LoginRequest(
                    email = uiState.value.email,
                    password = uiState.value.password,
                )
            ).onSuccess{
                Log.v("Successful log in", "EmailVerifiedSuccessfullyViewModel")
                updateIsLoadingState(false)
                updateShowErrorDialogState(false)
                updateErrorState(null)
                updateIsSuccessfulState(true)
            }.onError {error->
                Log.v("Failed log in", "EmailVerifiedSuccessfullyViewModel")
                updateIsLoadingState(false)
                updateShowErrorDialogState(true)
                updateErrorState(error)
                updateIsSuccessfulState(false)
            }
        }
    }
}

