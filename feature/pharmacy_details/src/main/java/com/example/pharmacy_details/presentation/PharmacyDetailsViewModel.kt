package com.example.pharmacy_details.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_cases.employee_account_management.DeactivatePharmacyUseCase
import com.example.domain.use_cases.employee_account_management.ReactivatePharmacyUseCase
import com.example.domain.use_cases.pharmacy.GetPharmacyDetailsUseCase
import com.example.model.account_management.DeactivateUserAccountRequest
import com.example.model.enums.ScreenState
import com.example.model.pharmacy.PharmacyDetailsResponse
import com.example.model.role_config.RoleAppConfig
import com.example.pharmacy_details.navigation.PharmacyAccessType
import com.example.pharmacy_details.navigation.PharmacyDetailsRoute
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.Error
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PharmacyDetailsViewModel(
    private val getPharmacyDetailsUseCase: GetPharmacyDetailsUseCase,
    private val deactivatePharmacyUseCase: DeactivatePharmacyUseCase,
    private val reactivatePharmacyUseCase: ReactivatePharmacyUseCase,
    private val roleAppConfig: RoleAppConfig,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PharmacyDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<PharmacyDetailsRoute>()
        updateNavArgs(route.pharmacyAccessType, route.pharmacyId)
        getPharmacyDetails()
    }

    fun getUiActions(
        navActions: PharmacyDetailsNavigationUiActions,
    ): PharmacyDetailsUiActions = PharmacyDetailsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): PharmacyDetailsBusinessUiActions =
        object : PharmacyDetailsBusinessUiActions {
            override fun onDeactivateAccount() {
                deactivatePharmacy()
            }

            override fun onReactivateAccount() {
                reactivatePharmacy()
            }

            override fun onHideErrorDialog() {
                setErrorDialogState(false, null)
            }

            override fun onRefresh() {
                refreshPharmacyData()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }

        }

    private fun updateNavArgs(profileAccessType: PharmacyAccessType, employeeId: Int?) {
        _uiState.update {
            it.copy(
                pharmacyAccessType = profileAccessType,
                pharmacyId = employeeId
            )
        }
    }

    private fun updateProfileInfoState(profileInfo: PharmacyDetailsResponse?) {
        _uiState.update { it.copy(pharmacyInfo = profileInfo) }
        Log.v("imgUrl", profileInfo?.userWithAddress?.imageUrl ?: "")
    }

    private fun updateProfileScreenState(screenState: ScreenState) {
        _uiState.update { it.copy(profileScreenState = screenState) }
    }

    private fun getPharmacyDetails() {
        val pharmacyId = uiState.value.pharmacyId
        if (pharmacyId == null) return
        viewModelScope.launch {
            updateProfileScreenState(ScreenState.LOADING)
            Log.v("Fetching Doctor Profile", "PharmacyDetailsViewModel")
            getPharmacyDetailsUseCase(pharmacyId)
                .onSuccess { data ->
                    Log.v("PharmacyDetails fetched Successfully", "PharmacyDetailsViewModel")
                    updateProfileScreenState(ScreenState.SUCCESS)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch PharmacyDetails", "PharmacyDetailsViewModel")
                    updateProfileScreenState(ScreenState.ERROR)
                    updateProfileInfoState(null)
                }
        }
    }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun refreshPharmacyData() {
        val pharmacyId = uiState.value.pharmacyId
        if (pharmacyId == null) return
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Fetching Doctor Profile", "PharmacyDetailsViewModel")
            getPharmacyDetailsUseCase(pharmacyId)
                .onSuccess { data ->
                    Log.v("PharmacyDetails fetched Successfully", "PharmacyDetailsViewModel")
                    updateIsRefreshing(false)
                    updateProfileInfoState(data)
                    if (uiState.value.profileScreenState == ScreenState.ERROR) {
                        updateProfileScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed to fetch PharmacyDetails", "PharmacyDetailsViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }


    private fun setLoadingDialogState(showLoadingDialog: Boolean, text: UiText?) {
        _uiState.update { it.copy(showLoadingDialog = showLoadingDialog, loadingDialogText = text) }
    }

    private fun setErrorDialogState(showErrorDialog: Boolean, text: UiText?) {
        _uiState.update { it.copy(showErrorDialog = showErrorDialog, errorDialogText = text) }
    }

    private fun updateIsDeactivatedSuccessfullyState(isSuccessful: Boolean) {
        _uiState.update { it.copy(isAccountDeactivatedSuccessfully = isSuccessful) }
    }

    private fun updatePharmacyDeactivationErrorState(error: Error?) {
        _uiState.update { it.copy(accountDeactivationError = error) }
    }

    private fun deactivatePharmacy() {
        viewModelScope.launch {
            setLoadingDialogState(true, UiText.StringResource(R.string.deactivating))
            Log.v("Deactivating Pharmacy", "ProfileViewModel")
            deactivatePharmacyUseCase(
                deactivateUserAccountRequest = DeactivateUserAccountRequest(
                    deactivationReason = "Feeling Sick",
                    role = roleAppConfig.role,
                    userId = null,
                ),
                pharmacyId = uiState.value.pharmacyId,
            ).onSuccess {
                Log.v("Pharmacy Deactivated Successfully", "DoctorProfileViewModel")
                setLoadingDialogState(false, null)
                setErrorDialogState(false, null)
                updatePharmacyDeactivationErrorState(null)
                updateIsDeactivatedSuccessfullyState(true)
                getPharmacyDetails()
            }.onError { error ->
                Log.v("Failed to Deactivate Pharmacy", "DoctorProfileViewModel")
                setLoadingDialogState(false, null)
                setErrorDialogState(
                    true,
                    UiText.StringResource(R.string.failed_to_deactivate_account)
                )
                updatePharmacyDeactivationErrorState(error)
                updateIsDeactivatedSuccessfullyState(false)
            }
        }
    }

    private fun reactivatePharmacy() {
        viewModelScope.launch {
            setLoadingDialogState(true, UiText.StringResource(R.string.reactivating))
            Log.v("Reactivating Account", "ProfileViewModel")
            reactivatePharmacyUseCase(
                pharmacyId = uiState.value.pharmacyId
            ).onSuccess {
                Log.v("Account Reactivated Successfully", "DoctorProfileViewModel")
                setLoadingDialogState(false, null)
                setErrorDialogState(false, null)
                updatePharmacyDeactivationErrorState(null)
                updateIsDeactivatedSuccessfullyState(true)
                getPharmacyDetails()
            }.onError {result->
                Log.v("Failed to Reactivate Account", "DoctorProfileViewModel")
                setLoadingDialogState(false, null)
                setErrorDialogState(
                    true,
                    UiText.StringResource(R.string.failed_to_reactivate_account)
                )
                updatePharmacyDeactivationErrorState(result)
                updateIsDeactivatedSuccessfullyState(false)
            }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }

}