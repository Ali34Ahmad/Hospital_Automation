package com.example.upload_employee_documents.main

import android.net.Uri


fun mockUploadEmployeeDocumentsNavigationUiActions()=object : UploadEmployeeDocumentsNavigationUiActions {
    override fun navigateToAddResidentialAddressScreen() {

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
