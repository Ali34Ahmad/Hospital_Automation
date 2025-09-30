package com.example.network.model.dto.clinic

import com.example.network.model.dto.workday.WorkDayDto
import com.example.network.model.dto.user.UserDto
import com.example.network.serializer.LocalDateSerializer
import com.example.network.serializer.LocalTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class ClinicFullDto(
    val clinicId: Int,
    @SerialName("first_available_time")
    @Serializable(with = LocalTimeSerializer::class)
    val firstAvailableTime: LocalTime? = null,//need some elaboration
    val name: String,
    @SerialName("provides_vaccines")
    val providesVaccines: Boolean,
    @SerialName("is_deactivated")
    val isDeactivated: Boolean,
    @SerialName("deactivation_reason")
    val deactivationReason: String? = null,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("creation_date")
    val creationDate: LocalDate,
    @SerialName("closing_date")
    @Serializable(with = LocalDateSerializer::class)
    val closingDate: LocalDate? = null,
    val createdAt: String,
    val updatedAt: String,
    @SerialName("deactivated_by")
    val deactivatedById: Int? = null,
    @SerialName("work_days")
    val workDays: List<WorkDayDto>,
    @SerialName("users")
    val activeDoctors: List<UserDto>,
    val deactivatedByUser: UserDto? = null,
    @SerialName("clinic_Services")
    val clinicServices: List<ClinicServiceDto> = emptyList()
)


//{
//    "data": {
//        "clinicId": 1,
//        "first_available_time": "08:00:00",
//        "name": "dentist",
//        "provides_vaccines": false,
//        "is_deactivated": false,
//        "deactivation_reason": null,
//        "creation_date": "2033-06-15",
//        "closing_date": null,
//        "createdAt": "2025-06-13T12:44:08.000Z",
//        "updatedAt": "2025-06-13T12:44:08.000Z",
//        "deactivated_by": null,
//        "work_days": [
//            {
//                "work_DaysId": 1,
//                "day": "SATURDAY",
//                "work_start_time": "06:49:57",
//                "work_end_time": "06:49:57",
//                "createdAt": "2025-06-21T18:22:36.000Z",
//                "updatedAt": "2025-06-21T18:22:36.000Z",
//                "pharmacy_id": null,
//                "doctor_id": 122,
//                "clinic_id": 1
//            },
//            {
//                "work_DaysId": 2,
//                "day": "MONDAY",
//                "work_start_time": "06:49:57",
//                "work_end_time": "06:49:57",
//                "createdAt": "2025-06-21T18:22:36.000Z",
//                "updatedAt": "2025-06-21T18:22:36.000Z",
//                "pharmacy_id": null,
//                "doctor_id": 122,
//                "clinic_id": 1
//            }
//        ],
//        "users": [
//            {
//                "userId": 122,
//                "first_name": "Ali",
//                "middle_name": "Bassam",
//                "last_name": "Mansoura",
//                "imgurl": "public/images/1749483085979-8825.jpeg",
//                "specialty": "dentist"
//            }
//        ],
//        "clinic_Services": [
//            {
//                "Clinic_ServicesId": 1,
//                "name": "surgery service",
//                "description": "surgery service description",
//                "createdAt": "2025-06-22T10:43:42.000Z",
//                "updatedAt": "2025-06-22T10:43:42.000Z",
//                "clinic_id": 1
//            },
//            {
//                "Clinic_ServicesId": 2,
//                "name": "vaccine service",
//                "description": "vaccine service description",
//                "createdAt": "2025-06-22T10:43:42.000Z",
//                "updatedAt": "2025-06-22T10:43:42.000Z",
//                "clinic_id": 1
//            }
//        ],
//        "deactivated": null
//    }
//}