package com.example.doctor_app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.doctor_app.main.AppViewModel
import com.example.doctor_app.navigation.Navigation

import com.example.doctor_schedule.presentation.DoctorScheduleNavigationActions
import com.example.doctor_schedule.presentation.DoctorScheduleScreen
import com.example.doctor_schedule.presentation.DoctorScheduleViewModel
import com.example.ui.theme.Hospital_AutomationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hospital_AutomationTheme {
                val appViewModel = koinViewModel<AppViewModel>()
                val appUiState = appViewModel.uiState.collectAsState()

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT, Color.TRANSPARENT,
                        detectDarkMode = {
                            appUiState.value.isDarkTheme
                        }),
                    navigationBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT, Color.TRANSPARENT,
                        detectDarkMode = {
                            appUiState.value.isDarkTheme
                        }),
                )

                Hospital_AutomationTheme(
                    darkTheme = appUiState.value.isDarkTheme
                ) {
                    Navigation()
                }
            }
        }
    }
}
