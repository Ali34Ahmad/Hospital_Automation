package com.example.admin_app.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.user_preferences.GetUserPreferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(AppUIState())
    val uiState = _uiState.onStart {
        readAppTheme()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        _uiState.value
    )

    private fun readAppTheme() {
        viewModelScope.launch {
            getUserPreferencesUseCase().collect { userPreference ->
                updateIsDarkTheme(userPreference.isDarkTheme)
            }
        }
    }

    private fun updateIsDarkTheme(isDarkTheme: Boolean) {
        _uiState.update { it.copy(isDarkTheme = isDarkTheme) }
    }
}