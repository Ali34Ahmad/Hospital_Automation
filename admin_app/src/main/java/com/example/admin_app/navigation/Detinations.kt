package com.example.admin_app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.clinics_search.navigation.ClinicsSearchRoute
import com.example.constants.icons.AppIcons
import com.example.doctors.navigation.DoctorSearchRoute
import com.example.employees_search.navigation.EmployeesSearchRoute
import com.example.pharmacies_search.navigation.PharmaciesSearchSearchRoute
import com.example.ui_components.R

sealed class BottomNavItem(
    val route: Any,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
    ) {
        object Doctors : BottomNavItem(
            DoctorSearchRoute(),
            R.string.doctors,
            AppIcons.Outlined.doctors
        )
        object Employees : BottomNavItem(
            EmployeesSearchRoute,
            R.string.employees,
            AppIcons.Outlined.employees
        )
        object Clinics : BottomNavItem(
            ClinicsSearchRoute(hasAdminAccess = true),
            R.string.clinics,
            AppIcons.Outlined.clinics
        )
        object Pharmacies : BottomNavItem(
            PharmaciesSearchSearchRoute,
            R.string.pharmacies,
            AppIcons.Outlined.pharmacy
        )

        companion object {
            val items = listOf(
                Doctors,
                Employees,
                Clinics,
                Pharmacies,
            )
        }
    }