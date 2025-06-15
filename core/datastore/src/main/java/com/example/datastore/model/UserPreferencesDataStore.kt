package com.example.datastore.model

data class UserPreferencesDataStore(
    val isDarkTheme: Boolean,
    val showPermissionCard: Boolean,
    val token: String?,
)
