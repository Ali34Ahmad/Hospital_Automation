package com.example.data.mapper.employment_history

import com.example.model.employment_history.EmploymentHistoryResponse
import com.example.model.employment_history.UserDetails
import com.example.model.employment_history.UserReference
import com.example.model.user.FullName
import com.example.network.model.response.profile.EmploymentHistoryResponseDto
import com.example.network.model.response.profile.UserDetailsDto
import com.example.network.model.response.profile.UserReferenceDto
import com.example.network.utility.ApiRoutes

fun EmploymentHistoryResponseDto.toEmploymentHistoryResponse(): EmploymentHistoryResponse {
    return EmploymentHistoryResponse(
        currentUser = this.currentUser.toUserDetails(),
        employeeDocumentsFileSize=this.employeeDocumentsFileSize,
        resignedBy = this.resignedBy?.toUserReference(),
        suspendedBy = this.suspendedBy?.toUserReference(),
        acceptedBy = this.acceptedBy?.toUserReference()
    )
}

fun UserDetailsDto.toUserDetails(): UserDetails {
    return UserDetails(
        userId = this.userId,
        fullName = FullName(
            firstName = this.firstName,
            middleName = this.middleName,
            lastName = this.lastName
        ),
        subInfo=subInfo,
        isResigned = this.isResigned,
        acceptedBy = this.acceptedBy,
        suspendedBy = this.suspendedBy,
        resignedBy = this.resignedBy,
        imageUrl = "${ApiRoutes.BASE_URL}/${ this.imageUrl }",
        workStartDate = this.workStartDate,
        workEndDate = this.workEndDate,
        documentsFileUrl = "${ApiRoutes.BASE_URL}/${this.documentsFileUrl}"
    )
}

fun UserReferenceDto.toUserReference(): UserReference {
    return UserReference(
        userId = this.userId,
        fullName = FullName(
            firstName = this.firstName,
            middleName = this.middleName,
            lastName = this.lastName
        )
    )
}