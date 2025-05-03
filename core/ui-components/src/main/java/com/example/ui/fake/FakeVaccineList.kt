package com.example.ui.fake

import com.example.constants.enums.AgeUnit
import com.example.model.Age
import com.example.model.VaccineWithDescription

fun createSampleVaccineList(): List<VaccineWithDescription> {
    return listOf(
        VaccineWithDescription(
            id = 1,
            name = "Influenza Vaccine",
            description = "Protects against seasonal influenza viruses.",
            isAvailable = true,
            fromAge = Age(6, AgeUnit.MONTH),
            toAge = Age(100, AgeUnit.YEAR) // Assuming no upper age limit
        ),
        VaccineWithDescription(
            id = 2,
            name = "MMR Vaccine",
            description = "Protects against measles, mumps, and rubella.Protects against measles, mumps, and rubella. Protects against measles, mumps, and rubella.",
            isAvailable = false,
            fromAge = Age(12, AgeUnit.MONTH),
            toAge = Age(60, AgeUnit.YEAR) // Example upper age limit
        )
    )
}