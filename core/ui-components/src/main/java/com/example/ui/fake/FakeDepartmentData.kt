package com.example.ui.fake

import androidx.compose.ui.util.fastCbrt
import com.example.model.Department
import com.example.ui_components.components.list.DepartmentDoctorsLazyRow
import java.time.LocalDate

fun createSampleDepartmentData(): List<Department> {
    return listOf(
        Department(
            id = 101,
            name = "Cardiology Department",
            workDays = createSampleWorkDayList(),
            activeDoctors = createDoctorList(), // Dr. Smith and Dr. Brown
            services = createSampleServiceData(),
            providesVaccine=true,
            creatingDate=LocalDate.now(),
            isAvailableNow = true,
        ),
        Department(
            id = 102,
            name = "Pediatrics Department",
            workDays = createSampleWorkDayList(), // Monday to Friday + Saturday half-day
            activeDoctors = createDoctorList(), // Dr. Johnson and Dr. Davis
            services =createSampleServiceData(),
            providesVaccine=false,
            creatingDate=LocalDate.now(),
            isAvailableNow = false,
        ),
        Department(
            id = 103,
            name = "Dermatology Department",
            workDays = createSampleWorkDayList(), // Monday to Friday + Saturday half-day
            activeDoctors = createDoctorList(), // Dr. Johnson and Dr. Davis
            services =createSampleServiceData(),
            providesVaccine=false,
            creatingDate=LocalDate.now(),
            isAvailableNow = true,
        )
    )
}