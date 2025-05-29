package com.example.upload_employee_profile_image

import android.net.Uri

class UploadEmployeeProfileImageUiActions(
    navigationActions:UploadEmployeeProfileImageNavigationUiActions,
    businessActions:UploadEmployeeProfileImageBusinessUiActions,
) :UploadEmployeeProfileImageBusinessUiActions by businessActions,
   UploadEmployeeProfileImageNavigationUiActions by navigationActions


interface UploadEmployeeProfileImageBusinessUiActions {
    fun onImageUploadingCancelled()
    fun onUploadImage(uri: Uri)
    fun onShowErrorDialogStateChange(isShown: Boolean)
}

interface UploadEmployeeProfileImageNavigationUiActions {
    fun navigateToHomeScreenScreen()
}