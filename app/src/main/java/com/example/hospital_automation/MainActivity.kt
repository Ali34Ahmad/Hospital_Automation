package com.example.hospital_automation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import com.example.hospital_automation.main.AppViewModel
import com.example.hospital_automation.navigation.Navigation
import com.example.ui.theme.Hospital_AutomationTheme
import kotlinx.serialization.Serializable
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope


class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

    override val scope: Scope by activityScope()
}
