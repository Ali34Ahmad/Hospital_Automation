package com.example.ui.fake

import com.example.ui_components.model.Vaccine

fun createFakeVaccinationData(): List<Pair<Int, List<Vaccine>>> {
    return listOf(
        1 to listOf(
            Vaccine(name = "Vaccine A"),
            Vaccine(name = "Vaccine B"),
            Vaccine(name = "Vaccine C"),
        ),
        2 to listOf(
            Vaccine(name = "Vaccine D"),
        ),
        3 to listOf(
            Vaccine(name = "Vaccine E"),
            Vaccine(name = "Vaccine F"),
            Vaccine(name = "Vaccine G"),
        )
    )
}