package com.example.ui.fake

import com.example.model.VaccineWithDescription
import com.example.model.helper.Age
import com.example.model.helper.AgeUnit

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