package com.example.employee_profile.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.auth.LogoutUseCase
import com.example.domain.use_cases.employee_account_management.DeactivateUserAccountUseCase
import com.example.domain.use_cases.employee_account_management.ReactivateMyAccountUseCase
import com.example.domain.use_cases.employee_account_management.ResignUserAccountUseCase
import com.example.domain.use_cases.employee_profile.GetCurrentEmployeeProfileUseCase
import com.example.domain.use_cases.employee_profile.GetEmployeeProfileByIdUseCase
import com.example.employee_profile.navigation.EmployeeProfileRoute
import com.example.employee_profile.navigation.EmployeeProfileAccessType
import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.auth.logout.LogoutRequest
import com.example.model.employee.EmployeeProfileResponse
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

class EmployeeProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val deactivateUserAccountUseCase: DeactivateUserAccountUseCase,
    private val reactivateMyAccountUseCase: ReactivateMyAccountUseCase,
    private val getCurrentEmployeeProfileUseCase: GetCurrentEmployeeProfileUseCase,
    private val getEmployeeProfileByIdUseCase: GetEmployeeProfileByIdUseCase,
    private val resignUserAccountUseCase: ResignUserAccountUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val roleAppConfig: RoleAppConfig,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmployeeProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<EmployeeProfileRoute>()
        updateNavArgs(route.employeeProfileAccessType, route.employeeId)
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
            override fun onDeactivateAccount() {
                deactivateAccount()
            }

            override fun onReactivateMyAccount() {
                reactivateMyAccount()
            }


            override fun onLogout() {
                logout()
            }

            override fun onResign() {
                resignUser()
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

            override fun onUpdateDeactivationReason(value:String) {
                updateDeactivationReasonText(value)
            }

            override fun onHideDeactivationReasonDialog() {
                updateDeactivationReasonDialogVisibility(false)
            }

            override fun onShowDeactivationReasonDialog() {
                updateDeactivationReasonDialogVisibility(true)
            }


        }

    private fun updateDeactivationReasonDialogVisibility(isVisible: Boolean){
        _uiState.update { it.copy(isWriteDeactivationReasonDialogShown = isVisible) }
    }

    private fun updateNavArgs(employeeProfileAccessType: EmployeeProfileAccessType, employeeId: Int?) {
        _uiState.update {
            it.copy(
                employeeProfileAccessType = employeeProfileAccessType,
                employeeId = employeeId
            )
        }
    }

    private fun updateDeactivationReasonText(value:String){
        _uiState.update { it.copy(deactivationReasonText = value) }
    }

    private fun updateProfileInfoState(profileInfo: EmployeeProfileResponse?) {
        _uiState.update { it.copy(userInfo = profileInfo) }
        Log.v("imgUrl", profileInfo?.profile?.imageUrl ?: "")
    }

    private fun updateProfileScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(profileScreenState = screenState) }
    }

    private fun getCurrentEmployeeProfile() {
        viewModelScope.launch {
            updateProfileScreenState(ScreenState.LOADING)
            Log.v("Fetching Employee Profile", "EmployeeProfileViewModel")
            getCurrentEmployeeProfileUseCase()
                .onSuccess { data ->
                    Log.v("EmployeeProfile fetched Successfully", "EmployeeProfileViewModel")
                    updateProfileScreenState(ScreenState.SUCCESS)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch EmployeeProfile", "EmployeeProfileViewModel")
                    updateProfileScreenState(ScreenState.ERROR)
                    updateProfileInfoState(null)
                }
        }
    }

    private fun getEmployeeProfileById() {
        val employeeId = uiState.value.employeeId
        if (employeeId == null) return
        viewModelScope.launch {
            updateProfileScreenState(ScreenState.LOADING)
            Log.v("Fetching Employee Profile", "EmployeeProfileViewModel")
            getEmployeeProfileByIdUseCase(employeeId)
                .onSuccess { data ->
                    Log.v("EmployeeProfile fetched Successfully", "EmployeeProfileViewModel")
                    updateProfileScreenState(ScreenState.SUCCESS)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch EmployeeProfile", "EmployeeProfileViewModel")
                    updateProfileScreenState(ScreenState.ERROR)
                    updateProfileInfoState(null)
                }
        }
    }

    private fun getEmployeeProfile() {
        when (uiState.value.employeeProfileAccessType) {
            EmployeeProfileAccessType.TOKEN_ACCESS -> getCurrentEmployeeProfile()
            EmployeeProfileAccessType.EMPLOYEE_ID_ACCESS -> getEmployeeProfileById()
            EmployeeProfileAccessType.ADMIN_ACCESS -> getEmployeeProfileById()
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
                    Log.v("Logged out Successfully", "EmployeeProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(false, null)
                    updateLogoutErrorState(null)
                    updateIsLoggedOutSuccessfullyState(true)
                }.onError { error ->
                    Log.v("Logging out Failed", "EmployeeProfileViewModel")
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

    private fun deactivateAccount() {
        viewModelScope.launch {
            setLoadingDialogState(true, UiText.StringResource(R.string.deactivating))
            Log.v("Deactivating Account", "ProfileViewModel")
            deactivateUserAccountUseCase(
                deactivateUserAccountRequest = DeactivateUserAccountRequest(
                    deactivationReason = uiState.value.deactivationReasonText,
                    role =roleAppConfig.role,
                    userId = uiState.value.employeeId
                )
            ).onSuccess {
                Log.v("Account Deactivated Successfully", "EmployeeProfileViewModel")
                setLoadingDialogState(false, null)
                setErrorDialogState(false, null)
                updateAccountDeactivationErrorState(null)
                updateIsDeactivatedSuccessfullyState(true)
                updateDeactivationReasonText("")
                getEmployeeProfile()
            }.onError { error ->
                Log.v("Failed to Deactivate Account", "EmployeeProfileViewModel")
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
                role = roleAppConfig.role,
                userId = uiState.value.employeeId,
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Account Reactivated Successfully", "EmployeeProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(false, null)
                    updateAccountDeactivationErrorState(null)
                    updateIsDeactivatedSuccessfullyState(true)
                    getEmployeeProfile()
                }

                is Result.Error -> {
                    Log.v("Failed to Reactivate Account", "EmployeeProfileViewModel")
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

    private fun resignUser() {
        viewModelScope.launch {
            setLoadingDialogState(true, UiText.StringResource(R.string.reactivating))
            Log.v("Resign Account", "ProfileViewModel")
            val result = resignUserAccountUseCase(
                userId = uiState.value.employeeId,
            )
            when (result) {
                is Result.Success -> {
                    Log.v("Account Resigned Successfully", "EmployeeProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(false, null)
                    updateAccountDeactivationErrorState(null)
                    updateIsDeactivatedSuccessfullyState(true)
                    getEmployeeProfile()
                }

                is Result.Error -> {
                    Log.v("Failed to Resign Account", "EmployeeProfileViewModel")
                    setLoadingDialogState(false, null)
                    setErrorDialogState(
                        true,
                        UiText.StringResource(R.string.failed_to_resign_user)
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
        when (uiState.value.employeeProfileAccessType) {
            EmployeeProfileAccessType.TOKEN_ACCESS -> refreshCurrentEmployeeData()
            EmployeeProfileAccessType.EMPLOYEE_ID_ACCESS -> refreshEmployeeByIdData()
            EmployeeProfileAccessType.ADMIN_ACCESS -> refreshEmployeeByIdData()
            null -> null
        }
    }

    private fun refreshCurrentEmployeeData() {
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Employee Profile", "EmployeeProfileViewModel")
            getCurrentEmployeeProfileUseCase()
                .onSuccess { data ->
                    Log.v("EmployeeProfile fetched Successfully", "EmployeeProfileViewModel")
                    updateIsRefreshing(false)
                    updateProfileInfoState(data)
                    if (uiState.value.profileScreenState == ScreenState.ERROR) {
                        updateProfileScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch EmployeeProfile", "EmployeeProfileViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun refreshEmployeeByIdData() {
        val employeeId = uiState.value.employeeId
        if (employeeId == null) return
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Employee Profile", "EmployeeProfileViewModel")
            getEmployeeProfileByIdUseCase(employeeId)
                .onSuccess { data ->
                    Log.v("EmployeeProfile fetched Successfully", "EmployeeProfileViewModel")
                    updateIsRefreshing(false)
                    updateProfileInfoState(data)
                    if (uiState.value.profileScreenState == ScreenState.ERROR) {
                        updateProfileScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch EmployeeProfile", "EmployeeProfileViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }

}