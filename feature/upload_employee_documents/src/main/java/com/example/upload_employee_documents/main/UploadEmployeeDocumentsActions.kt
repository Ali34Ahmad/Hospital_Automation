package com.example.upload_employee_documents.main

import android.net.Uri

class UploadEmployeeDocumentsUiActions(
    navigationActions:UploadEmployeeDocumentsNavigationUiActions,
    businessActions:UploadEmployeeDocumentsBusinessUiActions,
) :UploadEmployeeDocumentsBusinessUiActions by businessActions,
   UploadEmployeeDocumentsNavigationUiActions by navigationActions


interface UploadEmployeeDocumentsBusinessUiActions {
    fun onFileUploadingPause()
    fun onFileUploadingOpen()
    fun onFileUploadingResume()
    fun onUploadFileButtonClick()
    fun onUploadFile(uri: Uri)
    fun onShowErrorDialogStateChange(value: Boolean)
    fun onCancelFileUpload()
}

interface UploadEmployeeDocumentsNavigationUiActions {
    fun navigateToAddResidentialAddressScreen()
}