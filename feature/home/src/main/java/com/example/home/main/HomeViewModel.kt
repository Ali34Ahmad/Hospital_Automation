package com.example.home.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import com.example.domain.use_cases.user_preferences.UpdateIsDarkThemeUseCase
import com.example.domain.use_cases.user_preferences.UpdateShowPermissionCardUseCase
import com.example.model.enums.ScreenState
import com.example.model.role_config.RoleAppConfig
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val updateIsDarkThemeUseCase: UpdateIsDarkThemeUseCase,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val updateShowPermissionCardUseCase: UpdateShowPermissionCardUseCase,
    private val checkEmployeePermissionUseCase: CheckEmployeePermissionUseCase,
    private val roleAppConfig:RoleAppConfig,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        readShowPermissionCard()
        readTheme()
        checkEmployeePermission()
    }

    fun getUiActions(
        navActions: HomeNavigationUiActions,
    ): HomeUiActions = HomeUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): HomeBusinessUiActions = object : HomeBusinessUiActions {
        override fun onStartButtonClick() {
            viewModelScope.launch { writeShowPermissionCard(false) }
        }

        override fun onChangeTheme() {
            changeTheme()
        }

        override fun onRefresh() {
            refreshData()
        }

        override fun clearToastMessage() {
            updateToastMessage(null)
        }

    }

    private fun updateShowPermissionCard(showPermissionCard: Boolean) {
        _uiState.update { it.copy(showPermissionCard = showPermissionCard) }
    }

    private fun readShowPermissionCard() {
        viewModelScope.launch {
            getUserPreferencesUseCase().collect { userPreference ->
                updateShowPermissionCard(userPreference.showPermissionCard)
            }
        }
    }


    private suspend fun writeShowPermissionCard(showPermissionCard: Boolean) {
        updateShowPermissionCardUseCase(showPermissionCard)
    }

    private fun updateIsPermissionGranted(isPermissionGranted: Boolean) {
        _uiState.update { it.copy(isPermissionGranted = isPermissionGranted) }
    }

    private fun updateScreenState(screenState: ScreenState) {
        _uiState.update {
            it.copy(
                screenState = screenState
            )
        }
    }

    private fun checkEmployeePermission() {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING)
            Log.v("Checking Employee Permission", "HomeViewModel")
            checkEmployeePermissionUseCase(role = roleAppConfig.role)
                .onSuccess { data ->
                    Log.v("Successful Check Employee Permission", "HomeViewModel")
                    val isPermissionGranted = data.permissionGranted
                    updateIsPermissionGranted(isPermissionGranted)
                    if (!isPermissionGranted) {
                        writeShowPermissionCard(true)
                    }
                    updateScreenState(ScreenState.SUCCESS)
                }.onError { error ->
                    Log.v("Failed CheckingPermission", "HomeViewModel")
                    updateScreenState(ScreenState.ERROR)
                    updateIsPermissionGranted(false)
                }
        }
    }


    private fun changeTheme() {
        viewModelScope.launch {
            updateIsDarkThemeUseCase(
                !uiState.value.isDarkTheme
            )
        }
    }

    private fun readTheme() {
        viewModelScope.launch {
            getUserPreferencesUseCase().collect { userPreference ->
                updateIsDarkTheme(userPreference.isDarkTheme)
            }
        }
    }

    private fun updateIsDarkTheme(isDarkTheme: Boolean) {
        _uiState.update { it.copy(isDarkTheme = isDarkTheme) }
    }

    private fun updateIsRefreshing(isRefreshing: Boolean) {
        _uiState.update { it.copy(isRefreshing = isRefreshing) }
    }

    private fun refreshData() {
        viewModelScope.launch {
            updateIsRefreshing(true)
            Log.v("Refreshing Employee Permission", "HomeViewModel")
            checkEmployeePermissionUseCase(role = roleAppConfig.role)
                .onSuccess { data ->
                    Log.v("Successful Refresh Employee Permission", "HomeViewModel")
                    val isPermissionGranted = data.permissionGranted
                    updateIsPermissionGranted(isPermissionGranted)
                    if (!isPermissionGranted) {
                        writeShowPermissionCard(true)
                    }
                    updateIsRefreshing(false)
                    if (uiState.value.screenState == ScreenState.ERROR) {
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError { error ->
                    Log.v("Failed Refreshing Permission", "HomeViewModel")
                    updateIsRefreshing(false)
                    updateIsPermissionGranted(false)
                    updateToastMessage(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun updateToastMessage(uiText: UiText?) {
        _uiState.update { it.copy(toastMessage = uiText) }
    }
}

