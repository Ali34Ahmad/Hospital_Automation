package com.example.employee_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.request.DeactivateMyEmployeeAccountRequest
import com.example.network.model.response.EmployeeProfileResponse
import com.example.network.remote.account_management.EmployeeAccountManagementApiService
import com.example.network.remote.auth.AuthApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.utility.network.Error
import com.example.utility.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeProfileViewModel(
    private val authService: AuthApiService,
    private val employeeAccountManagementService: EmployeeAccountManagementApiService,
    private val employeeProfileApiService: EmployeeProfileApiService
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
            Log.v("Submitting log in info", "LoginViewModel")
            val result = employeeProfileApiService.getEmployeeInfo()
            when (result) {
                is Result.Success -> {
                    Log.v("EmployeeProfile fetched Successfully", "EmployeeProfileViewModel")
                    updateIsLoadingProfileState(false)
                    updateProfileErrorState(null)
                    updateProfileInfoState(result.data)
                }

                is Result.Error -> {
                    Log.v("Failed to fetch EmployeeProfile", "EmployeeProfileViewModel")
                    updateIsLoadingProfileState(false)
                    updateProfileErrorState(result.error)
                    updateProfileInfoState(null)
                }
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
            val result = authService.logout()
            when (result) {
                is Result.Success -> {
                    Log.v("Logged out Successfully", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(false)
                    updateLogoutErrorState(null)
                    updateIsLoggedOutSuccessfullyState(true)
                }

                is Result.Error -> {
                    Log.v("Logging out Failed", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(true, "Failed to logout. Please try again later")
                    updateLogoutErrorState(result.error)
                    updateIsLoggedOutSuccessfullyState(false)
                }
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
            setLoadingDialogState(true, "Logging out...")
            Log.v("Deactivating Account", "ProfileViewModel")
            val result = employeeAccountManagementService.deactivateMyEmployeeAccount(
                deactivateMyEmployeeAccountRequest = DeactivateMyEmployeeAccountRequest("Feeling Sick")
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Account Deactivated Successfully", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(false)
                    updateAccountDeactivationErrorState(null)
                    updateIsDeactivatedSuccessfullyState(true)
                }

                is Result.Error -> {
                    Log.v("Failed to Deactivate Account", "EmployeeProfileViewModel")
                    setLoadingDialogState(false)
                    setErrorDialogState(
                        true,
                        "Failed to deactivate your account. Please try again later"
                    )
                    updateAccountDeactivationErrorState(result.error)
                    updateIsDeactivatedSuccessfullyState(false)
                }
            }
        }
    }

}