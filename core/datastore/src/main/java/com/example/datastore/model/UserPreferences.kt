package com.example.datastore.model

data class UserPreferences(
    val isDarkTheme: Boolean,
    val showPermissionCard: Boolean,
    val token: String?,
)
