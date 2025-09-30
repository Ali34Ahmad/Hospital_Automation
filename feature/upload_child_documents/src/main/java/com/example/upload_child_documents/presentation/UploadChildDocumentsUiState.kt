package com.example.upload_child_documents.presentation

import android.net.Uri
import com.example.constants.enums.FileUploadingState
import com.example.model.FileInfo
import com.example.util.UiText

data class UploadChildDocumentsUiState(
    val childId: Int? = null,
    val fileUri: Uri? = null,
    val fileInfo: FileInfo? = null,
    val fileUploadingState: FileUploadingState = FileUploadingState.UPLOADING,
    val isNextButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val errorDialogText: UiText? = null,
    val showSuccessCard: Boolean = false,
)