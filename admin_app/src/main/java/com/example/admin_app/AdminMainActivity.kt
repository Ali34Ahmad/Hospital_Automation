package com.example.admin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.admin_app.main.AppViewModel
import com.example.appointment_details.presentation.AppointmentDetailsAction
import com.example.appointment_details.presentation.AppointmentDetailsNavigationActions
import com.example.appointment_details.presentation.AppointmentDetailsScreen
import com.example.appointment_details.presentation.AppointmentDetailsViewModel
import com.example.child_profile.presentation.ChildProfileNavigationAction
import com.example.child_profile.presentation.ChildProfileScreen
import com.example.child_profile.presentation.ChildProfileViewModel
import com.example.children.presentation.ChildrenNavigationAction
import com.example.children.presentation.ChildrenScreen
import com.example.children.presentation.ChildrenViewModel
import com.example.children_search.presentation.ChildrenSearchNavigationActions
import com.example.children_search.presentation.ChildrenSearchScreen
import com.example.children_search.presentation.ChildrenSearchViewModel
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
import com.example.guardian_profile.presentation.GuardianProfileNavigationAction
import com.example.guardian_profile.presentation.GuardianProfileScreen
import com.example.guardian_profile.presentation.GuardianProfileViewModel
import com.example.medicine_details.presentation.MedicineDetailsNavigationActions
import com.example.medicine_details.presentation.MedicineDetailsScreen
import com.example.medicine_details.presentation.MedicineDetailsViewModel
import com.example.pharmacies_search.presentation.PharmaciesSearchNavigationActions
import com.example.pharmacies_search.presentation.PharmaciesSearchScreen
import com.example.pharmacies_search.presentation.PharmaciesSearchViewModel
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
                val viewModel = koinViewModel<PharmacyMedicinesViewModel>()
                val navActions=  object : PharmacyMedicinesNavigationActions{
                    override fun navigateUp() {

                    }

                    override fun navigateToPharmacy(pharmacyId: Int) {

                    }

                    override fun navigateToMedicineDetails(medicineId: Int) {

                    }
                }
                PharmacyMedicineScreen(
                    viewModel,
                    navActions
                )
        }}
    }
}