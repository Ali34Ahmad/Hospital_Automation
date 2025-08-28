package com.example.data.mapper.prescription

import com.example.model.medicine.DetailedPrescriptionMedicine
import com.example.model.medicine.PrescriptionMedicineMainInfo
import com.example.network.model.response.medicine.DetailedPrescriptionMedicineDto
import com.example.network.model.response.medicine.PrescriptionMedicineMainInfoDto

fun DetailedPrescriptionMedicineDto.toPrescriptionMedicineMainInfo()=
    DetailedPrescriptionMedicine(
        id = id,
        medicine = medicine.toPrescriptionMedicineMainInfo(),
        fulfilledBy = fulfilledBy,
        note = note,
    )


fun PrescriptionMedicineMainInfoDto.toPrescriptionMedicineMainInfo()=
    PrescriptionMedicineMainInfo(
        id = id,
        name = name,
        imageUrl = imgUrl,
        titer = titer,
    )

