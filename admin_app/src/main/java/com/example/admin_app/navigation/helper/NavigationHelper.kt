package com.example.admin_app.navigation.helper

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.example.doctors.navigation.DoctorSearchRoute

fun getDoctorDestinationOrNull(
    backStackEntry: NavBackStackEntry?,
) = try {
    backStackEntry?.toRoute<DoctorSearchRoute>()
}catch (_: Exception){
    null
}
