package com.example.ui.fake

import com.example.model.age.Age
import com.example.model.enums.AgeUnit
import com.example.model.vaccine.VaccineData

fun createSampleVaccineList(): List<VaccineData> {
    return listOf(
        VaccineData(
            id = 1,
            name = "Influenza Vaccine",
            description = "Protects against seasonal influenza viruses.",
            minAge = Age(6, AgeUnit.MONTH),
            maxAge = Age(100, AgeUnit.YEAR),
            quantity = 10,
            interactions = createVaccineInteractionsSampleData() // Assuming no upper age limit
        ),
        VaccineData(
            id = 1,
            name = "Influenza Vaccine",
            description = "Protects against seasonal influenza viruses.",
            minAge = Age(6, AgeUnit.MONTH),
            maxAge = Age(100, AgeUnit.YEAR),
            quantity = 10,
            interactions = createVaccineInteractionsSampleData() // Assuming no upper age limit
        ),
    )
}