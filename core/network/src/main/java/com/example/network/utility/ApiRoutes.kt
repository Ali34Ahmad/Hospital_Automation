package com.example.network.utility

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
    const val SEARCH_FOR_CHILDREN_ADDED_BY_EMPLOYEE_BY_NAME = "$EMPLOYEE/search-children-added-by-employee"
    //users
    const val SHOW_USER_PROFILE = "$EMPLOYEE/find-user-byId"
    const val SEARCH_FOR_USER = "$EMPLOYEE/searchFor-user"
    const val GUARDIAN_FOR_CHILD = "$EMPLOYEE/guardian-for-ex-child"
    const val USERS_BY_NAME = "$EMPLOYEE/find-user-byname"
    const val GUARDIANS_BY_CHILD_ID = "$EMPLOYEE/show-guardian_for_child"

    const val SIGNUP_EMPLOYEE = "$EMPLOYEE/sign-up"
    const val LOGIN_EMPLOYEE = "$EMPLOYEE/login"
    const val LOGOUT_EMPLOYEE = "$EMPLOYEE/logout"
    const val SEND_OTP = "$EMPLOYEE/send-otp"
    const val VERIFY_OTP = "$EMPLOYEE/verify-otp"
    const val ADD_RESIDENTIAL_ADDRESS = "$EMPLOYEE/add-address"

    const val UPLOAD_EMPLOYEE_DOCUMENTS = "$EMPLOYEE/upload-file"
    const val UPLOAD_EMPLOYEE_PROFILE_IMAGE ="$EMPLOYEE/add-image"
    const val EMPLOYEE_PROFILE = "$EMPLOYEE/show-profile"
    const val FIND_EMPLOYEE_BY_ID = "$EMPLOYEE/find-employee-by-id"
    const val RESET_PASSWORD ="$EMPLOYEE/reset-password"

    const val CHECK_EMPLOYEE_PERMISSION ="$EMPLOYEE/show-permissions"
    const val DEACTIVATE_MY_ACCOUNT = "$EMPLOYEE/deactivate-my-account"
    const val REACTIVATE_MY_ACCOUNT = "$EMPLOYEE/reactivate-my-account"
    const val EMPLOYMENT_HISTORY = "$EMPLOYEE/employment-history"

    const val ADMIN_PROFILE_BY_ID = "$EMPLOYEE/find-admin-by-id"

    const val SHOW_APPOINTMENTS = "$DOCTOR/show-appointments"

}