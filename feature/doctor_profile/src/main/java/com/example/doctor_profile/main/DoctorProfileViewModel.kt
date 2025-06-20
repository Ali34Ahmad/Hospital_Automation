package com.example.doctor_profile.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.doctor_profile.navigation.DoctorProfileRoute
import com.example.doctor_profile.navigation.ProfileAccessType
import com.example.domain.use_cases.auth.LogoutUseCase
import com.example.domain.use_cases.doctor.profile.GetCurrentDoctorProfileUseCase
import com.example.domain.use_cases.employee_account_management.DeactivateMyAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivateMyAccountUseCase
import com.example.model.account_management.DeactivateMyEmployeeAccountRequest
import com.example.model.auth.logout.LogoutRequest
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.model.enums.ScreenState
import com.example.model.role_config.RoleAppConfig
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.Error
import com.example.utility.network.Result
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoctorProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val deactivateMyAccountUseCase: DeactivateMyAccountUseCase,
    private val reactivateMyAccountUseCase: ReactivateMyAccountUseCase,
    private val getCurrentDoctorProfileUseCase: GetCurrentDoctorProfileUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val roleAppConfig: RoleAppConfig,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DoctorProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<DoctorProfileRoute>()
        updateNavArgs(route.profileAccessType, route.doctorId)
        getDoctorProfile()
    }

    fun getUiActions(
        navActions: DoctorProfileNavigationUiActions,
    ): DoctorProfileUiActions = DoctorProfileUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): DoctorProfileBusinessUiActions =
        object : DoctorProfileBusinessUiActions {
            override fun onRefreshProfile() {
                getDoctorProfile()
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
                setErrorDialogState(false, null)
            }

            override fun onRefresh() {
                refreshData()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

        }

    private fun updateNavArgs(profileAccessType: ProfileAccessType, employeeId: Int?) {
        _uiState.update {
            it.copy(
                profileAccessType = profileAccessType,
                doctorId = employeeId
            )
        }
    }

    private fun updateProfileInfoState(profileInfo: DoctorProfileResponse?) {
        _uiState.update { it.copy(doctorInfo = profileInfo) }
        Log.v("imgUrl", profileInfo?.profile?.imageUrl ?: "")
    }

    private fun updateProfileScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(profileScreenState = screenState) }
    }

    private fun getCurrentDoctorProfile() {
        viewModelScope.launch {
            updateProfileScreenState(ScreenState.LOADING)
            Log.v("Fetching Doctor Profile", "DoctorProfileViewModel")
            getCurrentDoctorProfileUseCase()
                .onSuccess { data ->
                    Log.v("DoctorProfile fetched Successfully", "DoctorProfileViewModel")
                    updateProfileScreenState(ScreenState.SUCCESS)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch DoctorProfile", "DoctorProfileViewModel")
                    updateProfileScreenState(ScreenState.ERROR)
                    updateProfileInfoState(null)
                }
        }
    }

    private fun getDoctorProfile() {
        when (uiState.value.profileAccessType) {
            ProfileAccessType.TOKEN_ACCESS -> getCurrentDoctorProfile()
            ProfileAccessType.ID_ACCESS -> TODO()
            null -> null
        }
    }

    private fun setLoadingDialogState(showLoadingDialog: Boolean, text: UiText?) {
        _uiState.update { it.copy(showLoadingDialog = showLoadingDialog, loadingDialogText = text) }
    }

    private fun setErrorDialogState(showErrorDialog: Boolean, text: UiText?) {
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
            setLoadingDialogState(true, UiText.StringResource(R.string.logging_out))
            Log.v("Logging out", "ProfileViewModel")
            logoutUseCase(
                logoutRequest = LogoutRequest(
                    role = roleAppConfig.role,
                )
            )
                .onSuccess { data ->
                    Log.v("Logged out Successfully", "DoctorProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(false, null)
                    updateLogoutErrorState(null)
                    updateIsLoggedOutSuccessfullyState(true)
                }.onError { error ->
                    Log.v("Logging out Failed", "DoctorProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(true, UiText.StringResource(R.string.failed_to_log_out))
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
            setLoadingDialogState(true, UiText.StringResource(R.string.deactivating))
            Log.v("Deactivating Account", "ProfileViewModel")
            deactivateMyAccountUseCase(
                deactivateMyEmployeeAccountRequest = DeactivateMyEmployeeAccountRequest(
                    deactivationReason = "Feeling Sick",
                    role =roleAppConfig.role,
                )
            ).onSuccess {
                Log.v("Account Deactivated Successfully", "DoctorProfileViewModel")
                setLoadingDialogState(false, null)
                setErrorDialogState(false, null)
                updateAccountDeactivationErrorState(null)
                updateIsDeactivatedSuccessfullyState(true)
                getDoctorProfile()
            }.onError { error ->
                Log.v("Failed to Deactivate Account", "DoctorProfileViewModel")
                setLoadingDialogState(false, null)
                setErrorDialogState(
                    true,
                    UiText.StringResource(R.string.failed_to_deactivate_account)
                )
                updateAccountDeactivationErrorState(error)
                updateIsDeactivatedSuccessfullyState(false)
            }
        }
    }

    private fun reactivateMyAccount() {
        viewModelScope.launch {
            setLoadingDialogState(true, UiText.StringResource(R.string.reactivating))
            Log.v("Reactivating Account", "ProfileViewModel")
            val result = reactivateMyAccountUseCase(
                role = roleAppConfig.role
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Account Reactivated Successfully", "DoctorProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(false, null)
                    updateAccountDeactivationErrorState(null)
                    updateIsDeactivatedSuccessfullyState(true)
                    getDoctorProfile()
                }

                is Result.Error -> {
                    Log.v("Failed to Reactivate Account", "DoctorProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(
                        true,
                        UiText.StringResource(R.string.failed_to_reactivate_account)
                    )
                    updateAccountDeactivationErrorState(result.error)
                    updateIsDeactivatedSuccessfullyState(false)
                }
            }
        }
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun refreshData() {
        when (uiState.value.profileAccessType) {
            ProfileAccessType.TOKEN_ACCESS -> refreshCurrentDoctorData()
            ProfileAccessType.ID_ACCESS -> TODO()
            null -> null
        }
    }

    private fun refreshCurrentDoctorData() {
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Doctor Profile", "DoctorProfileViewModel")
            getCurrentDoctorProfileUseCase()
                .onSuccess { data ->
                    Log.v("DoctorProfile fetched Successfully", "DoctorProfileViewModel")
                    updateIsRefreshing(false)
                    updateProfileInfoState(data)
                    if (uiState.value.profileScreenState == ScreenState.ERROR) {
                        updateProfileScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch DoctorProfile", "DoctorProfileViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }

}