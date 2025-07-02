package com.example.doctor_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.appointment_details.presentation.AppointmentDetailsScreen
import com.example.appointment_details.presentation.AppointmentDetailsViewModel
import com.example.appointment_details.presentation.AppointmentNavigationActions
import com.example.clinic_details.presentation.ClinicDetailsScreen
import com.example.clinic_details.presentation.ClinicDetailsUIAction
import com.example.clinic_details.presentation.ClinicDetailsViewModel
import com.example.clinic_details.presentation.ClinicNavigationAction
import com.example.clinics_search.presentation.ClinicsSearchNavigationActions
import com.example.clinics_search.presentation.ClinicsSearchScreen
import com.example.clinics_search.presentation.ClinicsSearchViewModel

import com.example.doctor_schedule.presentation.DoctorScheduleNavigationActions
import com.example.doctor_schedule.presentation.DoctorScheduleScreen
import com.example.doctor_schedule.presentation.DoctorScheduleViewModel
import com.example.medical_diagnosis.presentation.DiagnosisNavigationActions
import com.example.medical_diagnosis.presentation.DiagnosisScreen
import com.example.medical_diagnosis.presentation.DiagnosisViewModel
import com.example.ui.theme.Hospital_AutomationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hospital_AutomationTheme {
                val viewModel = koinViewModel<ClinicDetailsViewModel>()
                ClinicDetailsScreen(
                    viewModel = viewModel,
                    navigationActions = object : ClinicNavigationAction {
                        override fun navigateUp() {
                        }

                        override fun navigateToDoctorProfile(doctorId: Int) {
                        }

                        override fun navigateToInitialScreen(doctorId: Int) {
                        }

                        override fun navigateToVaccines(clinicId: Int) {
                        }
                    },
                )
            }
        }
    }
}
