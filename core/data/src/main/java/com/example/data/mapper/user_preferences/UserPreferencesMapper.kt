package com.example.data.mapper.user_preferences

import com.example.datastore.model.UserPreferencesDataStore
import com.example.model.user_preferences.UserPreferences

fun UserPreferencesDataStore.toUserPreferences() =
    UserPreferences(
        isDarkTheme = this.isDarkTheme,
        showPermissionCard = this.showPermissionCard,
        token = this.token
    )