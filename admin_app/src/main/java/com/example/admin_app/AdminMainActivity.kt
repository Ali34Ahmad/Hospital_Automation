package com.example.admin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.admin_app.main.AppViewModel
import com.example.admin_app.navigation.AdminGraph
import com.example.doctor_schedule.presentation.ScheduleNavigationActions
import com.example.doctor_schedule.presentation.ScheduleScreen
import com.example.doctor_schedule.presentation.ScheduleViewModel
import com.example.pharmacy_medicines.presentation.PharmacyMedicineScreen
import com.example.pharmacy_medicines.presentation.PharmacyMedicinesNavigationActions
import com.example.pharmacy_medicines.presentation.PharmacyMedicinesViewModel
import com.example.ui.theme.Hospital_AutomationTheme
import org.koin.androidx.compose.koinViewModel

class AdminMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = koinViewModel<AppViewModel>()
            val appUIState = mainViewModel.uiState.collectAsStateWithLifecycle()
            Hospital_AutomationTheme(darkTheme = appUIState.value.isDarkTheme) {
                AdminGraph()
            }
        }
    }
}