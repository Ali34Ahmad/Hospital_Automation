package com.example.ui.fake

import com.example.model.Doctor

fun createDoctorList(): List<Doctor> {
    return listOf(
        Doctor(
            imageUrl = "https://example.com/doctor1.jpg",
            name = "Dr. John Smith",
            specialty = "Cardiology"
        ),
        Doctor(
            imageUrl = "https://example.com/doctor2.jpg",
            name = "Dr. Emily Johnson",
            specialty = "Pediatrics"
        ),
        Doctor(
            imageUrl = "https://example.com/doctor3.jpg",
            name = "Dr. David Lee",
            specialty = "Dermatology"
        ),
        Doctor(
            imageUrl = "https://example.com/doctor4.jpg",
            name = "Dr. Maria Garcia",
            specialty = "Neurology"
        ),
        Doctor(
            imageUrl = "https://example.com/doctor5.jpg",
            name = "Dr. Robert Brown",
            specialty = "Orthopedics"
        )
        // Add more doctors as needed
    )
}