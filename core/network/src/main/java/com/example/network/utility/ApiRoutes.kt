package com.example.network.utility

import com.example.network.model.enums.RoleDto

object ApiRoutes {

    const val BASE_URL = "https://dispensary-hkz3.onrender.com"
    private const val EMPLOYEE = "$BASE_URL/employee"
    private const val DOCTOR = "$BASE_URL/doctor"


    const val SEARCH_FOR_CHILD = "$EMPLOYEE/searchFor-child"
    const val ADD_CHILD = "$EMPLOYEE/addChild"
    const val UPLOAD_CHILD_CERTIFICATE = "$EMPLOYEE/upload-Child-Certificate"
    const val CHILD_BY_ID = "$EMPLOYEE/find-child-byId"
    const val CHILDREN_BY_NAME = "$EMPLOYEE/find-child-byname"
    const val CHILDREN_BY_GUARDIAN_ID = "$EMPLOYEE/show-children_for_guardian"
    const val CHILDREN_BY_EMPLOYEE_ID = "$EMPLOYEE/show-children-added-by-employee"
    const val SEARCH_FOR_CHILDREN_ADDED_BY_EMPLOYEE_BY_NAME =
        "$EMPLOYEE/search-children-added-by-employee"

    //users
    const val SHOW_USER_PROFILE = "$EMPLOYEE/find-user-byId"
    const val SEARCH_FOR_USER = "$EMPLOYEE/searchFor-user"
    const val GUARDIAN_FOR_CHILD = "$EMPLOYEE/guardian-for-ex-child"
    const val USERS_BY_NAME = "$EMPLOYEE/find-user-byname"
    const val GUARDIANS_BY_CHILD_ID = "$EMPLOYEE/show-guardian_for_child"

    const val EMPLOYEE_SIGNUP = "$EMPLOYEE/sign-up"
    const val EMPLOYEE_LOGIN = "$EMPLOYEE/login"
    const val EMPLOYEE_LOGOUT = "$EMPLOYEE/logout"
    const val EMPLOYEE_SEND_OTP = "$EMPLOYEE/send-otp"
    const val EMPLOYEE_VERIFY_OTP = "$EMPLOYEE/verify-otp"
    const val ADD_RESIDENTIAL_ADDRESS = "$EMPLOYEE/add-address"

    const val UPLOAD_EMPLOYMENT_DOCUMENTS = "$EMPLOYEE/upload-file"
    const val UPLOAD_PROFILE_IMAGE = "$EMPLOYEE/add-image"
    const val EMPLOYEE_PROFILE = "$EMPLOYEE/show-profile"
    const val FIND_EMPLOYEE_BY_ID = "$EMPLOYEE/find-employee-by-id"
    const val EMPLOYEE_RESET_PASSWORD = "$EMPLOYEE/reset-password"

    const val CHECK_EMPLOYEE_PERMISSION = "$EMPLOYEE/show-permissions"
    const val DEACTIVATE_MY_EMPLOYEE_ACCOUNT = "$EMPLOYEE/deactivate-my-account"
    const val REACTIVATE_MY_EMPLOYEE_ACCOUNT = "$EMPLOYEE/reactivate-my-account"
    const val EMPLOYEE_EMPLOYMENT_HISTORY = "$EMPLOYEE/employment-history"

    const val ADMIN_PROFILE_BY_ID = "$EMPLOYEE/find-admin-by-id"


    object Doctor {
        private const val DOCTOR = "$BASE_URL/doctor"
        const val SIGNUP = "$DOCTOR/sign-up"
        const val LOGIN = "$DOCTOR/login"
        const val SEND_OTP = "$DOCTOR/send-otp"
        const val VERIFY_OTP = "$DOCTOR/verify-otp"
        const val LOGOUT = "$DOCTOR/logout"
        const val RESET_PASSWORD = "$DOCTOR/reset-password"
        const val ADD_RESIDENTIAL_ADDRESS = "$DOCTOR/add-address"
        const val UPLOAD_EMPLOYMENT_FILE = "$DOCTOR/upload-file"
        const val UPLOAD_PROFILE_IMAGE = "$DOCTOR/add-image"
        const val DEACTIVATE_MY_ACCOUNT = "$DOCTOR/deactivate-my-account"
        const val REACTIVATE_MY_ACCOUNT = "$DOCTOR/reactivate-my-account"
        const val EMPLOYMENT_HISTORY = "$DOCTOR/Doctor-employment-history"
        const val CHECK_PERMISSION = "$DOCTOR/show-permissions"
        const val PROFILE = "$DOCTOR/show-profile"

        const val ADD_NEW_VACCINE = "$DOCTOR/add-new-vaccine"
        const val GET_VACCINE_BY_ID = "$DOCTOR/view-single-vaccine-details"
        const val GET_GENERIC_VACCINATION_TABLE = "$DOCTOR/get-generic-vaccination-table"
    }

    fun loginEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_LOGIN
            RoleDto.DOCTOR -> Doctor.LOGIN
            RoleDto.ADMIN -> ""
        }
    }

    fun verifyEmailEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_VERIFY_OTP
            RoleDto.DOCTOR -> Doctor.VERIFY_OTP
            RoleDto.ADMIN -> ""
        }
    }

    fun sendOtpToEmailEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_SEND_OTP
            RoleDto.DOCTOR -> Doctor.SEND_OTP
            RoleDto.ADMIN -> ""
        }
    }

    fun singUpEndpointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_SIGNUP
            RoleDto.DOCTOR -> Doctor.SIGNUP
            RoleDto.ADMIN -> ""
        }
    }

    fun resetPasswordEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_RESET_PASSWORD
            RoleDto.DOCTOR -> Doctor.RESET_PASSWORD
            RoleDto.ADMIN -> ""
        }
    }

    fun logoutEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_LOGOUT
            RoleDto.DOCTOR -> Doctor.LOGOUT
            RoleDto.ADMIN -> ""
        }
    }

    fun addResidentialAddressEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> ADD_RESIDENTIAL_ADDRESS
            RoleDto.DOCTOR -> Doctor.ADD_RESIDENTIAL_ADDRESS
            RoleDto.ADMIN -> ""
        }
    }

    fun uploadEmploymentDocumentsEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> UPLOAD_EMPLOYMENT_DOCUMENTS
            RoleDto.DOCTOR -> Doctor.UPLOAD_EMPLOYMENT_FILE
            RoleDto.ADMIN -> ""
        }
    }

    fun uploadProfileImageEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> UPLOAD_PROFILE_IMAGE
            RoleDto.DOCTOR -> Doctor.UPLOAD_PROFILE_IMAGE
            RoleDto.ADMIN -> ""
        }
    }


    fun deactivateMyAccountEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> DEACTIVATE_MY_EMPLOYEE_ACCOUNT
            RoleDto.DOCTOR -> Doctor.DEACTIVATE_MY_ACCOUNT
            RoleDto.ADMIN -> ""
        }
    }

    fun reactivateMyAccountEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> REACTIVATE_MY_EMPLOYEE_ACCOUNT
            RoleDto.DOCTOR -> Doctor.REACTIVATE_MY_ACCOUNT
            RoleDto.ADMIN -> ""
        }
    }

    fun checkPermissionEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> CHECK_EMPLOYEE_PERMISSION
            RoleDto.DOCTOR -> Doctor.CHECK_PERMISSION
            RoleDto.ADMIN -> ""
        }
    }

    fun employmentHistoryEndPointFor(role: RoleDto): String {
        return when (role) {
            RoleDto.EMPLOYEE -> EMPLOYEE_EMPLOYMENT_HISTORY
            RoleDto.DOCTOR -> Doctor.EMPLOYMENT_HISTORY
            RoleDto.ADMIN -> ""
        }
    }


    const val SHOW_APPOINTMENTS = "$DOCTOR/show-appointments"

}