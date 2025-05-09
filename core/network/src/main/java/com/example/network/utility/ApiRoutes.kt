package com.example.network.utility

object ApiRoutes {
    private const val BASE_URL = "https://dispensary-hkz3.onrender.com"
    private const val EMPLOYEE = "$BASE_URL/employee"


    const val SHOW_CHILD_PROFILE = "$EMPLOYEE/show-For-child"
    const val SEARCH_FOR_CHILD = "$EMPLOYEE/searchFor-child"
    const val ADD_CHILD = "$EMPLOYEE/addChild/2"
    const val UPLOAD_CHILD_CERTIFICATE = "$EMPLOYEE/upload-Child-Certificate"

    const val SHOW_USER_PROFILE = "$EMPLOYEE/show-For-user"
    const val SEARCH_FOR_USER = "$EMPLOYEE/searchFor-user"
    const val GUARDIAN_FOR_CHILD = "$EMPLOYEE/guardian-for-ex-child"


    const val SIGNUP_EMPLOYEE = "$EMPLOYEE/sign-up"
    const val LOGIN_EMPLOYEE = "$EMPLOYEE/login"
    const val VERIFY_OTP = "$EMPLOYEE/verify-otp"
    const val UPLOAD_EMPLOYEE_DOCUMENTS = "$EMPLOYEE/upload-license"
    const val ADD_RESIDENTIAL_ADDRESS = "$EMPLOYEE/add-address"
    const val EMPLOYEE_PROFILES = "$EMPLOYEE/show-profile"

}