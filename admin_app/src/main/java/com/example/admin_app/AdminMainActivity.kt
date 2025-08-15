package com.example.admin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.admin_app.main.AppViewModel
import com.example.clinic_details.presentation.ClinicDetailsScreen
import com.example.clinic_details.presentation.ClinicDetailsViewModel
import com.example.clinic_details.presentation.ClinicNavigationAction
import com.example.clinics_search.presentation.ClinicsSearchNavigationActions
import com.example.clinics_search.presentation.ClinicsSearchScreen
import com.example.clinics_search.presentation.ClinicsSearchViewModel
import com.example.doctor_schedule.presentation.DoctorScheduleNavigationActions
import com.example.doctor_schedule.presentation.DoctorScheduleScreen
import com.example.doctor_schedule.presentation.DoctorScheduleViewModel
import com.example.doctors.presentation.DoctorSearchScreen
import com.example.doctors.presentation.DoctorsSearchNavigationActions
import com.example.doctors.presentation.DoctorsSearchViewModel
import com.example.employees_search.presentation.EmployeeSearchViewModel
import com.example.employees_search.presentation.EmployeesSearchNavigationActions
import com.example.employees_search.presentation.EmployeesSearchScreen
import com.example.pharmacies_search.presentation.PharmaciesSearchNavigationActions
import com.example.pharmacies_search.presentation.PharmaciesSearchScreen
import com.example.pharmacies_search.presentation.PharmaciesSearchViewModel
import com.example.ui.theme.Hospital_AutomationTheme
import org.koin.androidx.compose.koinViewModel

class AdminMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = koinViewModel<AppViewModel>()
            val appUIState = mainViewModel.uiState.collectAsStateWithLifecycle()
            Hospital_AutomationTheme(darkTheme = appUIState.value.isDarkTheme) {
                val viewModel = koinViewModel<ClinicDetailsViewModel>()
                ClinicDetailsScreen(
                    viewModel = viewModel,
                    navigationActions = object : ClinicNavigationAction{
                        override fun navigateUp() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToDoctorProfile() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToScheduleScreen() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToVaccines() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToAllDoctors() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToAllAppointments() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToPrescriptions() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToMedicalRecords() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToContractHistory() {
                            TODO("Not yet implemented")
                        }

                        override fun navigateToEditClinic() {
                            TODO("Not yet implemented")
                        }

                    }
                )

        }}
    }
}