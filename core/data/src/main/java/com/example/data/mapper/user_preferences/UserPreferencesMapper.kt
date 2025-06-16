package com.example.data.mapper.user_preferences

import com.example.datastore.model.UserPreferences
import com.example.model.user_preferences.UserPreferencesDataStore

fun UserPreferences.toUserPreferences() =
    UserPreferencesDataStore(
        isDarkTheme = this.isDarkTheme,
        showPermissionCard = this.showPermissionCard,
        token = this.token
    )