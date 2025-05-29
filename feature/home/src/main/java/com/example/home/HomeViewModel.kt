package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.domain.repositories.EmployeeAccountManagementRepository
import com.example.domain.use_cases.employee_account_management.CheckEmployeePermissionUseCase
import com.example.utility.network.Error
import com.example.utility.network.Result
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val checkEmployeePermissionUseCase: CheckEmployeePermissionUseCase,
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

        override fun onUpdateSelectedDrawerIndex(index: Int) {
            updateSelectedDrawerIndex(index)
        }

        override fun onChangeTheme() {
            changeTheme()
        }

    }

    private fun updateSelectedDrawerIndex(index: Int) {
        _uiState.update { it.copy(selectedDrawerIndex = index) }
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

    private fun updateShowPermissionCard(showPermissionCard: Boolean) {
        _uiState.update { it.copy(showPermissionCard = showPermissionCard) }
    }

    private fun readShowPermissionCard() {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.collect { userPreference ->
                updateShowPermissionCard(userPreference.showPermissionCard)
            }
        }
    }


    private suspend fun writeShowPermissionCard(showPermissionCard: Boolean) {
        userPreferencesRepository.updateShowPermissionCard(showPermissionCard)
    }

    private fun updateIsPermissionGranted(isPermissionGranted: Boolean) {
        _uiState.update { it.copy(isPermissionGranted = isPermissionGranted) }
    }

    private fun checkEmployeePermission() {
        viewModelScope.launch {
            updateIsLoadingState(true)
            Log.v("Checking Employee Permission", "HomeViewModel")
            checkEmployeePermissionUseCase()
                .onSuccess { data ->
                    Log.v("Successful Check Employee Permission", "HomeViewModel")
                    val isPermissionGranted = data.permissionGranted
                    updateIsPermissionGranted(isPermissionGranted)
                    if (!isPermissionGranted) {
                        writeShowPermissionCard(true)
                    }
                    updateIsLoadingState(false)
                    updateShowErrorDialogState(false)
                    updateErrorState(null)
                }.onError { error ->
                    Log.v("Failed CheckingPermission", "HomeViewModel")
                    updateIsLoadingState(false)
                    updateIsPermissionGranted(false)
                    updateShowErrorDialogState(true)
                    updateErrorState(error)
                }
        }
    }


    private fun changeTheme() {
        viewModelScope.launch {
            userPreferencesRepository.updateIsDarkTheme(
                !uiState.value.isDarkTheme
            )
        }
    }

    private fun readTheme() {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.collect { userPreference ->
                updateIsDarkTheme(userPreference.isDarkTheme)
            }
        }
    }

    private fun updateIsDarkTheme(isDarkTheme: Boolean) {
        _uiState.update { it.copy(isDarkTheme = isDarkTheme) }
    }

}

