package com.example.hospital_automation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastore.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    init {
        readAppTheme()
    }

    private fun readAppTheme() {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.collect { userPreference ->
                updateIsDarkTheme(userPreference.isDarkTheme)
            }
        }
    }

    private fun updateIsDarkTheme(isDarkTheme: Boolean) {
        _uiState.update { it.copy(isDarkTheme = isDarkTheme) }
    }

//    fun getUiActions(
//        navActions: AppNavigationUiActions,
//    ): AppUiActions = AppUiActions(
//        navigationActions = navActions,
//        businessActions = getBusinessUiActions()
//    )
//
//    private fun getBusinessUiActions(): AppBusinessUiActions =
//        object : AppBusinessUiActions {
//
//        }
//
}