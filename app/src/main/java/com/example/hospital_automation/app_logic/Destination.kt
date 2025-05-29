package com.example.hospital_automation.app_logic

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    object SignUp : Destination

    @Serializable
    data class EmailOtpVerification(
        val email: String,
        val password: String? = null,
        val navigateToResetPassword: Boolean
    ) : Destination

    @Serializable
    data class EmailVerifiedSuccessfully(
        val email: String,
        val password: String? = null,
        val navigateToResetPassword: Boolean
    ) : Destination

    @Serializable
    object UploadEmployeeDocuments : Destination

    @Serializable
    object AddResidentialAddress : Destination

    @Serializable
    object UploadEmployeeProfileImage : Destination

    @Serializable
    object EnterEmail : Destination

    @Serializable
    data class ResetPassword(val email: String) : Destination


    @Serializable
    object Login : Destination

    @Serializable
    object Home : Destination

    @Serializable
    object EmployeeProfile : Destination

    @Serializable
    object EmploymentHistory : Destination

    @Serializable
    data class AdminProfile(val adminId: Int) : Destination

}