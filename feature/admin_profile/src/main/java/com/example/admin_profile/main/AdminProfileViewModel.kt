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
        val routeArgs=savedStateHandle.toRoute<AdminProfileRoute>()
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
            override fun onRefreshProfile() {
                getAdminProfile()
            }
        }

    private fun updateIsLoadingProfileState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoadingProfile = isLoading) }
    }

    private fun updateProfileErrorState(error: Error?) {
        _uiState.update { it.copy(fetchingProfileError = error) }
    }

    private fun updateProfileInfoState(profileInfo: AdminProfileResponse?) {
        _uiState.update { it.copy(userInfo = profileInfo) }
    }


    private fun getAdminProfile() {
        viewModelScope.launch {
            updateIsLoadingProfileState(true)
            Log.v("Fetching Admin Profile", "AdminProfileViewModel")
            getAdminProfileByIdUseCase(uiState.value.adminId ?: -1)
                .onSuccess { data ->
                    Log.v("AdminProfile fetched Successfully", "AdminProfileViewModel")
                    updateIsLoadingProfileState(false)
                    updateProfileErrorState(null)
                    updateProfileInfoState(data)
                }.onError { error ->
                    Log.v("Failed to fetch AdminProfile", "AdminProfileViewModel")
                    updateIsLoadingProfileState(false)
                    updateProfileErrorState(error)
                    updateProfileInfoState(null)
                }
        }
    }

}