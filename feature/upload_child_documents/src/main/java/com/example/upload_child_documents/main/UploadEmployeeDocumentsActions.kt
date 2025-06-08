package com.example.upload_child_documents.main

import android.net.Uri

class UploadChildDocumentsUiActions(
    navigationActions:UploadChildDocumentsNavigationUiActions,
    businessActions:UploadChildDocumentsBusinessUiActions,
) :UploadChildDocumentsBusinessUiActions by businessActions,
   UploadChildDocumentsNavigationUiActions by navigationActions


interface UploadChildDocumentsBusinessUiActions {
    fun onFileUploadingPause()
    fun onFileUploadingOpen()
    fun onFileUploadingResume()
    fun onUploadFileButtonClick()
    fun onUploadFile(uri: Uri)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onCancelFileUpload()
}

interface UploadChildDocumentsNavigationUiActions {
    fun navigateToAddResidentialAddressScreen()
}