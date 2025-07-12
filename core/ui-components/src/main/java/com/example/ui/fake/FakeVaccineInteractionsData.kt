package com.example.ui.fake

import com.example.model.vaccine.VaccineInteraction

fun createVaccineInteractionsSampleData(): List<VaccineInteraction>{
    return  listOf(
        VaccineInteraction(name = "Immunodeficiency conditions", description = "Individuals with conditions like HIV/AIDS or other immune system disorders may also have a weakened response to the vaccine."),
        VaccineInteraction(name = "Neomycin or gelatin allergy", description = "The MMR vaccine contains trace amounts of neomycin and gelatin. Individuals with severe allergies to these substances may experience an allergic reaction."),
        VaccineInteraction(name = "Fever", description = "The MMR vaccine contains trace amounts of neomycin and gelatin. "),
    )
}