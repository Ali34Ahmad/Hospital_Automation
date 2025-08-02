package com.example.doctor_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.doctor_app.navigation.FakeNavigation
import com.example.ui.theme.Hospital_AutomationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hospital_AutomationTheme {
                FakeNavigation()
            }
        }
    }
}
