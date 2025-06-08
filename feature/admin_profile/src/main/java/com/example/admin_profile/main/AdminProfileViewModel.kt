package com.example.admin_profile.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.AdminProfileNavConstants
import com.example.admin_profile.navigation.AdminProfileRoute
import com.example.domain.use_cases.admin_profile.GetAdminProfileByIdUseCase
import com.example.model.admin_account.AdminProfileResponse
import com.example.model.enums.ScreenState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.Error
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminProfileViewModel(
    private val getAdminProfileByIdUseCase: GetAdminProfileByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AdminProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val routeArgs = savedStateHandle.toRoute<AdminProfileRoute>()
        _uiState.update {
            it.copy(
                adminId = routeArgs.adminId,
            )
        }
    }

    fun getUiActions(
        navActions: AdminProfileNavigationUiActions,
    ): AdminProfileUiActions = AdminProfileUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): AdminProfileBusinessUiActions =
        object : AdminProfileBusinessUiActions {
            override fun onGetAdminProfile() {
                getAdminProfile()
            }

            override fun onRefresh() {
                refreshEmploymentHistory()
            }

            override fun clearToastMessage() {
                updateToastMessage(null)
            }
        }

    private fun updateProfileInfoState(profileInfo: AdminProfileResponse?) {
        _uiState.update { it.copy(userInfo = profileInfo) }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update {
            it.copy(
                screenState = screenState
            )
        }
    }

    private fun getAdminProfile() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Fetching Admin Profile", "AdminProfileViewModel")
            getAdminProfileByIdUseCase(uiState.value.adminId ?: -1)
                .onSuccess { data ->
                    Log.v("AdminProfile fetched Successfully", "AdminProfileViewModel")
                    updateScreenState(ScreenState.Success)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch AdminProfile", "AdminProfileViewModel")
                    updateScreenState(ScreenState.ERROR)
                    updateProfileInfoState(null)
                }
        }
    }


    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun refreshEmploymentHistory() {
        viewModelScope.launch {
            val adminId = uiState.value.adminId
            if (adminId == null) return@launch
            updateIsRefreshing(true)
            Log.v("Fetching Admin Profile", "AdminProfileViewModel")
            getAdminProfileByIdUseCase(adminId)
                .onSuccess { data ->
                    Log.v("AdminProfile fetched Successfully", "AdminProfileViewModel")
                    updateIsRefreshing(false)
                    updateProfileInfoState(data)
                    updateScreenState(ScreenState.Success)
                }.onError { error ->
                    Log.v("Failed to fetch AdminProfile", "AdminProfileViewModel")
                    updateIsRefreshing(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }

}