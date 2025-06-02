package com.example.upload_employee_documents.main

import android.net.Uri
import com.example.constants.enums.FileLoadingState
import com.example.model.File
import com.example.utility.network.Error

data class UploadEmployeeDocumentsUiState(
    val fileUri: Uri? = null,
    val fileUiInfo: File? = null,
    val fileLoadingState: FileLoadingState = FileLoadingState.UPLOADING,
    val isNextButtonEnabled: Boolean = false,
    val isUploading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val errorDialogText: String = "",
    val isUploadingComplete: Boolean = false,
    val isUploadingCancelled: Boolean = false,
)