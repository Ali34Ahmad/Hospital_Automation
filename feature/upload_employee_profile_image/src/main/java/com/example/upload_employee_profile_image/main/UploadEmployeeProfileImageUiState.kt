package com.example.upload_employee_profile_image.main

import android.net.Uri
import com.example.constants.enums.FileLoadingState
import com.example.model.File
import com.example.utility.network.Error

data class UploadEmployeeProfileImageUiState(
    val uri: Uri? = null,
    val imageFile: File? = null,
    val uploadingState: FileLoadingState= FileLoadingState.Cancelled,
    val isUploadButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val error: Error? = null,
    val showErrorDialog: Boolean = false,
    val errorDialogText: String = "",
    val isSuccessful: Boolean = false,
)
