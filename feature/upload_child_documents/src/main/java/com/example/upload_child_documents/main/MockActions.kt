package com.example.upload_child_documents.main

import android.net.Uri


fun mockUploadEmployeeDocumentsNavigationUiActions()=object : UploadChildDocumentsNavigationUiActions {
    override fun navigateToAddResidentialAddressScreen() {

    }
}

fun mockUploadEmployeeDocumentsBusinessUiActions()=object : UploadChildDocumentsBusinessUiActions {
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
