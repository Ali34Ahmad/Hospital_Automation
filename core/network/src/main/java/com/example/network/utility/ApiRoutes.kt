package com.example.network.utility

object ApiRoutes {
    private const val BASE_URL = "FAKE_URL"
    private const val EMPLOYEE = "$BASE_URL/employee"


    const val SHOW_CHILD_PROFILE = "$EMPLOYEE/show-For-child"
    const val SEARCH_FOR_CHILD = "$EMPLOYEE/searchFor-child"

    const val SHOW_USER_PROFILE = "$EMPLOYEE/show-For-user"
    const val SEARCH_FOR_USER = "$EMPLOYEE/searchFor-user"

    const val SIGNUP_EMPLOYEE = "$EMPLOYEE/sign-up"
    const val LOGIN_EMPLOYEE = "$EMPLOYEE/login"
    const val VERIFY_OTP = "$EMPLOYEE/verify-otp"
    const val UPLOAD_EMPLOYEE_DOCUMENTS = "$EMPLOYEE/upload-license"
    const val ADD_RESIDENTIAL_ADDRESS = "$EMPLOYEE/add-address"
    const val EMPLOYEE_PROFILES = "$EMPLOYEE/show-profile"

}