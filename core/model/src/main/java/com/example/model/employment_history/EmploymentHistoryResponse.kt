package com.example.model.employment_history

import com.example.model.user.FullName
import java.time.LocalDate

data class EmploymentHistoryResponse(
    val currentUser: UserDetails,

    val resignedBy: UserReference?,

    val suspendedBy: UserReference?,

    val acceptedBy: UserReference?
)

data class UserDetails(
    val userId: Int,

    val fullName: FullName,

    val isResigned: Boolean,

    val acceptedBy: Int?,

    val suspendedBy: Int?,

    val resignedBy: Int?,

    val imageUrl: String?,

    val workStartDate: LocalDate?,

    val workEndDate: LocalDate?,
)

data class UserReference(
    val userId: Int,

    val fullName: FullName
)