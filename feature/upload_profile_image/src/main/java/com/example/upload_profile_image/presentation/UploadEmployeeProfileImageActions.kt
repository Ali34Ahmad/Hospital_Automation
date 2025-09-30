package com.example.upload_profile_image.presentation

import android.net.Uri

class UploadProfileImageUiActions(
    navigationActions:UploadProfileImageNavigationUiActions,
    businessActions:UploadProfileImageBusinessUiActions,
) :UploadProfileImageBusinessUiActions by businessActions,
   UploadProfileImageNavigationUiActions by navigationActions


interface UploadProfileImageBusinessUiActions {
    fun onImageUploadingCancelled()
    fun onUploadImage(uri: Uri)
    fun onShowErrorDialogStateChange(isShown: Boolean)
}

interface UploadProfileImageNavigationUiActions {
    fun navigateToHomeScreenScreen()
}