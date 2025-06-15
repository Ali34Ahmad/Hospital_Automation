package com.example.model.user_preferences

data class UserPreferences(
    val isDarkTheme: Boolean,
    val showPermissionCard: Boolean,
    val token: String?,
)
