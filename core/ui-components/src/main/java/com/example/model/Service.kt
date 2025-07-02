package com.example.model

import com.example.model.doctor.clinic.ClinicServiceData
import java.time.Duration

data class Service(
    val id: Int,
    val name: String,
    val description: String,
    val standardDuration: Duration = Duration.ZERO
){
    companion object{
        fun  fromClinicService(service: ClinicServiceData) = Service(
            id = service.id,
            name = service.name,
            description = service.description,
        )
    }
}
