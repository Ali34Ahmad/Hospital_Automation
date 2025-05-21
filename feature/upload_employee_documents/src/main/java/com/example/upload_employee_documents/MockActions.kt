package com.example.upload_employee_documents

import android.net.Uri


fun mockUploadEmployeeDocumentsNavigationUiActions()=object : UploadEmployeeDocumentsNavigationUiActions {
    override fun navigateToAddResidentialAddressScreen() {

    }

    override fun openPdfFileChooser() {

    }

}

fun mockUploadEmployeeDocumentsBusinessUiActions()=object : UploadEmployeeDocumentsBusinessUiActions {
    override fun onFileUploadingPause() {

    }

    override fun onFileUploadingOpen() {

    }

    override fun onFileUploadingResume() {

    }

    override fun onUploadFileButtonClick() {

    }

    override fun onUploadFile(uri: Uri) {

    }

    override fun onShowErrorDialogStateChange(value: Boolean) {

    }

    override fun onCancelFileUpload() {

    }
}
