package com.example.upload_employee_profile_image

import android.net.Uri


fun mockUploadEmployeeProfileImageNavigationUiActions()=object :
    UploadEmployeeProfileImageNavigationUiActions {
    override fun navigateToHomeScreenScreen() {

    }
}

fun mockUploadEmployeeProfileImageBusinessUiActions()=object :
    UploadEmployeeProfileImageBusinessUiActions {
    override fun onImageUploadingCancelled() {

    }

    override fun onUploadImage(uri: Uri) {

    }

    override fun onShowErrorDialogStateChange(isShown: Boolean) {

    }

}