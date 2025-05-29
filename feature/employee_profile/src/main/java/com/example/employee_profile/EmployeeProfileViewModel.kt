package com.example.employee_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repositories.AuthRepository
import com.example.domain.repositories.EmployeeAccountManagementRepository
import com.example.domain.repositories.EmployeeProfileRepository
import com.example.domain.use_cases.auth.LogoutUseCase
import com.example.domain.use_cases.employee_account_management.DeactivateMyEmployeeAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivateMyEmployeeAccountUseCase
import com.example.domain.use_cases.employee_profile.GetEmployeeProfileUseCase
import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.employee.EmployeeProfileResponse
import com.example.network.model.request.DeactivateMyEmployeeAccountRequestDto
import com.example.network.remote.account_management.EmployeeAccountManagementApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val deactivateMyEmployeeAccountUseCase: DeactivateMyEmployeeAccountUseCase,
    private val reactivateMyEmployeeAccountUseCase: ReactivateMyEmployeeAccountUseCase,
    private val getEmployeeProfileUseCase: GetEmployeeProfileUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmployeeProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getEmployeeProfile()
    }

    fun getUiActions(
        navActions: EmployeeProfileNavigationUiActions,
    ): EmployeeProfileUiActions = EmployeeProfileUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): EmployeeProfileBusinessUiActions =
        object : EmployeeProfileBusinessUiActions {
            override fun onRefreshProfile() {
                getEmployeeProfile()
            }

            override fun onDeactivateMyAccount() {
                deactivateMyAccount()
            }

            override fun onReactivateMyAccount() {
                reactivateMyAccount()
            }


            override fun onLogout() {
                logout()
            }

            override fun hideErrorDialog() {
                setErrorDialogState(false, "")
            }

        }

    private fun updateIsLoadingProfileState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoadingProfile = isLoading) }
    }

    private fun updateProfileErrorState(error: Error?) {
        _uiState.update { it.copy(fetchingProfileError = error) }
    }

    private fun updateProfileInfoState(profileInfo: EmployeeProfileResponse?) {
        _uiState.update { it.copy(userInfo = profileInfo) }
        Log.v("imgUrl", profileInfo?.profile?.imageUrl ?: "")
    }


    private fun getEmployeeProfile() {
        viewModelScope.launch {
            updateIsLoadingProfileState(true)
            Log.v("Fetching Employee Profile", "EmployeeProfileViewModel")
            getEmployeeProfileUseCase()
                .onSuccess { data ->
                    Log.v("EmployeeProfile fetched Successfully", "EmployeeProfileViewModel")
                    updateIsLoadingProfileState(false)
                    updateProfileErrorState(null)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch EmployeeProfile", "EmployeeProfileViewModel")
                    updateIsLoadingProfileState(false)
                    updateProfileErrorState(error)
                    updateProfileInfoState(null)
                }
        }
    }

    private fun setLoadingDialogState(showLoadingDialog: Boolean, text: String = "") {
        _uiState.update { it.copy(showLoadingDialog = showLoadingDialog, loadingDialogText = text) }
    }

    private fun setErrorDialogState(showErrorDialog: Boolean, text: String = "") {
        _uiState.update { it.copy(showErrorDialog = showErrorDialog, errorDialogText = text) }
    }

    private fun updateIsLoggedOutSuccessfullyState(isSuccessful: Boolean) {
        _uiState.update { it.copy(isLoggedOutSuccessfully = isSuccessful) }
    }

    private fun updateLogoutErrorState(error: Error?) {
        _uiState.update { it.copy(logOutError = error) }
    }

    private fun logout() {
        viewModelScope.launch {
            setLoadingDialogState(true, "Logging out...")
            Log.v("Logging out", "ProfileViewModel")
            logoutUseCase()
                .onSuccess { data ->
                    Log.v("Logged out Successfully", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(false)
                    updateLogoutErrorState(null)
                    updateIsLoggedOutSuccessfullyState(true)
                }.onError { error ->
                    Log.v("Logging out Failed", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(true, "Failed to logout. Please try again later")
                    updateLogoutErrorState(error)
                    updateIsLoggedOutSuccessfullyState(false)
                }
        }
    }


    private fun updateIsDeactivatedSuccessfullyState(isSuccessful: Boolean) {
        _uiState.update { it.copy(isAccountDeactivatedSuccessfully = isSuccessful) }
    }

    private fun updateAccountDeactivationErrorState(error: Error?) {
        _uiState.update { it.copy(accountDeactivationError = error) }
    }

    private fun deactivateMyAccount() {
        viewModelScope.launch {
            setLoadingDialogState(true, "Deactivating...")
            Log.v("Deactivating Account", "ProfileViewModel")
            deactivateMyEmployeeAccountUseCase(
                deactivateMyEmployeeAccountRequest = DeactivateMyEmployeeAccountRequest("Feeling Sick")
            ).onSuccess {
                Log.v("Account Deactivated Successfully", "EmployeeProfileViewModel")
                setLoadingDialogState(false)
                setErrorDialogState(false)
                updateAccountDeactivationErrorState(null)
                updateIsDeactivatedSuccessfullyState(true)
                getEmployeeProfile()
            }.onError { error ->
                Log.v("Failed to Deactivate Account", "EmployeeProfileViewModel")
                setLoadingDialogState(false)
                setErrorDialogState(
                    true,
                    "Failed to deactivate your account. Please try again later"
                )
                updateAccountDeactivationErrorState(error)
                updateIsDeactivatedSuccessfullyState(false)
            }
        }
    }

    private fun reactivateMyAccount() {
        viewModelScope.launch {
            setLoadingDialogState(true, "Reactivating...")
            Log.v("Reactivating Account", "ProfileViewModel")
            val result = reactivateMyEmployeeAccountUseCase()
            when (result) {
                is Result.Success -> {
                    Log.v("Account Reactivated Successfully", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(false)
                    updateAccountDeactivationErrorState(null)
                    updateIsDeactivatedSuccessfullyState(true)
                    getEmployeeProfile()
                }

                is Result.Error -> {
                    Log.v("Failed to Reactivate Account", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(
                        true,
                        "Failed to reactivate your account. Please try again later"
                    )
                    updateAccountDeactivationErrorState(result.error)
                    updateIsDeactivatedSuccessfullyState(false)
                }
            }
        }
    }


}