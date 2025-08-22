package com.example.model.child

import com.example.model.age.Age
import com.example.model.helper.ext.toAgeFromDate
import java.time.LocalDate

data class ChildFullData(
    val numberOfGuardians: Int? = null,
    val childId: Int? = null,
    val firstName: String,
    val lastName: String,
    val fatherFirstName: String,
    val fatherLastName: String,
    val motherFirstName: String,
    val motherLastName: String,
    val dateOfBirth: LocalDate,
    val birthCertificateImgUrl: String? = null,
    val gender: String,
    val employeeId: Int? = null,
    val employeeName: String? = null,
    val lastVaccination: LastVaccineData? = null,
    val lastAppointment: LastAppointmentData? = null,
    val nextVaccination: NextVaccineData? = null,
){
    val fullName: String
        get() = "$firstName $lastName"
    val age: Age
        get() = dateOfBirth.toAgeFromDate()
    val fatherFullName: String
        get() = "$fatherFirstName $fatherLastName"
    val motherFullName: String
        get() = "$motherFirstName $motherLastName"
}
