package com.example.data.mapper.prescription

import com.example.model.prescription.PrescriptionMedicineData
import com.example.network.model.dto.prescription.PrescriptionMedicineDto

    fun PrescriptionMedicineData.toPrescriptionMedicineDto() = PrescriptionMedicineDto(
        medicineId = medicineId,
        howToTake = howToTake,
    )
    fun PrescriptionMedicineDto.toPrescriptionMedicineData() = PrescriptionMedicineData(
        medicineId = medicineId,
        howToTake = howToTake
    )
