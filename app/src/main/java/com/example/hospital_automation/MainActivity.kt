package com.example.hospital_automation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.example.hospital_automation.fake_navigation.FakeGraph
import com.example.hospital_automation.ui.theme.Hospital_AutomationTheme
import com.example.hospital_automation.ui.theme.scrimDark
import com.example.hospital_automation.ui.theme.scrimLight

class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                scrimLight.toArgb(),
                scrimDark.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                scrimLight.toArgb(),
                scrimDark.toArgb()
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            Hospital_AutomationTheme {
                FakeGraph()
            }
        }
    }
}