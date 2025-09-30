package com.example.upload_profile_image.presentation

import android.net.Uri


fun mockUploadEmployeeProfileImageNavigationUiActions()=object :
    UploadProfileImageNavigationUiActions {
    override fun navigateToHomeScreenScreen() {

    }
}

fun mockUploadEmployeeProfileImageBusinessUiActions()=object :
    UploadProfileImageBusinessUiActions {
    override fun onImageUploadingCancelled() {

    }

    override fun onUploadImage(uri: Uri) {

    }

    override fun onShowErrorDialogStateChange(isShown: Boolean) {

    }

}