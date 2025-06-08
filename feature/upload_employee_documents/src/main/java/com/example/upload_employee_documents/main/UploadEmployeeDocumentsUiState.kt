package com.example.upload_employee_documents.main

import android.net.Uri
import com.example.constants.enums.FileUploadingState
import com.example.model.FileInfo
import com.example.util.UiText

data class UploadEmployeeDocumentsUiState(
    val fileUri: Uri? = null,
    val fileInfo: FileInfo? = null,
    val fileUploadingState: FileUploadingState = FileUploadingState.UPLOADING,
    val isNextButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val errorDialogText: UiText? = null,
)