package com.example.doctor_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

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
                val viewModel = koinViewModel<DoctorScheduleViewModel>()
                DoctorScheduleScreen(
                    modifier = Modifier.fillMaxSize(),
                    viewModel = viewModel,
                    navigationActions = object : DoctorScheduleNavigationActions{
                        override fun navigateToAppointmentDetails(id: Int) {
                        }

                    }
                )
            }
        }
    }
}
