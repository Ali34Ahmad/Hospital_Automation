package com.example.email_verification.email_verified_successfully.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.auth.LoginUseCase
import com.example.email_verification.EmailVerificationNavConstants
import com.example.model.auth.login.LoginRequest
import com.example.model.enums.ScreenState
import com.example.utility.network.Error
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
                password = savedStateHandle.get<String>(EmailVerificationNavConstants.PASSWORD)
                    ?: "",
                navigateToResetPassword = savedStateHandle.get<Boolean>(
                    EmailVerificationNavConstants.NAVIGATE_TO_RESET_PASSWORD
                ) ?: false,
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

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update {
            it.copy(
                screenState = screenState
            )
        }
    }

    private fun login() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Submitting log in info", "EmailVerifiedSuccessfullyViewModel")
            loginUseCase(
                loginRequest = LoginRequest(
                    email = uiState.value.email,
                    password = uiState.value.password,
                )
            ).onSuccess {
                Log.v("Successful log in", "EmailVerifiedSuccessfullyViewModel")
                updateScreenState(ScreenState.Success)
                updateShowErrorDialogState(false)
            }.onError { error ->
                Log.v("Failed log in", "EmailVerifiedSuccessfullyViewModel")
                updateScreenState(ScreenState.ERROR)
                updateShowErrorDialogState(true)
            }
        }
    }
}

