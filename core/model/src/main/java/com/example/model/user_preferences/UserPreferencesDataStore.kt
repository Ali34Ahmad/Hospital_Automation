package com.example.model.user_preferences

data class UserPreferencesDataStore(
    val isDarkTheme: Boolean,
    val showPermissionCard: Boolean,
    val token: String?,
)
