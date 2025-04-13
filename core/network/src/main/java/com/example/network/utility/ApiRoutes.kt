package com.example.network.utility

object ApiRoutes {
    private const val BASE_URL = "FAKE_URL"
    private const val EMPLOYEE = "$BASE_URL/employee"


    const val SHOW_CHILD_PROFILE = "$EMPLOYEE/show-For-child"
    const val SEARCH_FOR_CHILD = "$EMPLOYEE/searchFor-child"
    const val ADD_CHILD = "$EMPLOYEE/addChild"
    const val UPLOAD_CHILD_CERTIFICATE = "$EMPLOYEE/upload-Child-Certificate"

    const val SHOW_USER_PROFILE = "$EMPLOYEE/show-For-user"
    const val SEARCH_FOR_USER = "$EMPLOYEE/searchFor-user"
    const val GUARDIAN_FOR_CHILD = "$EMPLOYEE/guardian-for-ex-child"


}