package com.example.admin_app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.admin_app.main.AppViewModel
import com.example.admin_app.navigation.AdminGraph
import com.example.ui.theme.Hospital_AutomationTheme
import org.koin.androidx.compose.koinViewModel

class AdminMainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = koinViewModel<AppViewModel>()
            val appUiState = mainViewModel.uiState.collectAsStateWithLifecycle()

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
            val windowSizeClass = calculateWindowSizeClass(this)

            Hospital_AutomationTheme(darkTheme = appUiState.value.isDarkTheme) {
                AdminGraph(windowSizeClass=windowSizeClass)
            }
        }
    }
}