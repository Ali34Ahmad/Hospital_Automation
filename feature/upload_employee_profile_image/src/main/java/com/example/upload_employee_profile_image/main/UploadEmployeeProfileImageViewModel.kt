package com.example.upload_employee_profile_image.main

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.constants.enums.FileUploadingState
import com.example.domain.use_cases.upload_employee_profile_image.UploadEmployeeProfileImageUseCase
import com.example.model.FileInfo
import com.example.ui_components.R
import com.example.util.UiText
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import java.io.FileNotFoundException
import java.nio.channels.UnresolvedAddressException

class UploadEmployeeProfileImageViewModel(
    private val uploadEmployeeProfileImageUseCase: UploadEmployeeProfileImageUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UploadEmployeeProfileImageUiState())
    val uiState = _uiState.asStateFlow()

    private var uploadJob: Job? = null

    fun getUiActions(
        navActions: UploadEmployeeProfileImageNavigationUiActions,
    ): UploadEmployeeProfileImageUiActions = UploadEmployeeProfileImageUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): UploadEmployeeProfileImageBusinessUiActions =
        object : UploadEmployeeProfileImageBusinessUiActions {
            override fun onImageUploadingCancelled() {
                cancelUpload()
            }

            override fun onUploadImage(uri: Uri) {
                uploadImage(uri)
            }

            override fun onShowErrorDialogStateChange(isShown: Boolean) {
                updateShowErrorDialogState(isShown)
            }

        }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun uploadImage(uri: Uri) {
        uploadJob = uploadEmployeeProfileImageUseCase(uri)
            .onStart {
                setStartUploadingState(uri)
                Log.v("Uploading Image:", "Started")
            }
            .onEach {
                updateUploadingProgressState(
                    uploadingProgress = ((it.bytesSent.toFloat() / it.totalBytes.toFloat()) * 100).toInt(),
                    fileSize = it.totalBytes,
                )
                Log.v("Uploading Image:", "New Emit")
            }
            .onCompletion { cause ->
                if (cause == null) {
                    setSuccessfulUploadingState()
                } else if (cause is CancellationException) {
                    setCancelUploadingState()
                }
                Log.v("Uploading Image:", "Complete $cause")
            }
            .catch { cause ->
                val message = when (cause) {
                    is OutOfMemoryError -> {
                        UiText.StringResource(R.string.file_too_big)
                    }

                    is FileNotFoundException -> {
                        UiText.StringResource(R.string.file_not_found)
                    }

                    is UnresolvedAddressException -> {
                        UiText.StringResource(R.string.check_internet_connection)
                    }

                    else -> {
                        UiText.StringResource(R.string.something_went_wrong)
                    }
                }

                setFailureState(message)
                Log.v("Uploading File:",cause.message?:"Unknown Error")
            }
            .launchIn(viewModelScope)
    }

    private fun updateUploadingProgressState(uploadingProgress: Int, fileSize: Long) {
        _uiState.update {
            it.copy(
                imageFileInfo = FileInfo(
                    uploadingProgress = uploadingProgress,
                    fileSizeWithBytes = fileSize,
                    fileName = "FileName"
                ),
                showErrorDialog = false,
            )
        }
    }

    private fun setStartUploadingState(uri: Uri) {
        _uiState.update {
            it.copy(
                uri = uri,
                imageFileInfo = FileInfo(
                    uploadingProgress = 0,
                    fileSizeWithBytes = 0,
                    fileName = "FileName"
                ),
                showErrorDialog = false,
                uploadingState = FileUploadingState.UPLOADING,
            )
        }
    }

    private fun setFailureState(message: UiText?) {
        _uiState.update {
            it.copy(
                uri = null,
                imageFileInfo = null,
                errorDialogText = message,
                showErrorDialog = true,
                uploadingState = FileUploadingState.FAILED,
            )
        }
    }

    private fun cancelUpload() {
        uploadJob?.cancel()
        setCancelUploadingState()
    }

    private fun setCancelUploadingState() {
        _uiState.update {
            it.copy(
                uri = null,
                imageFileInfo = null,
                showErrorDialog = false,
                uploadingState = FileUploadingState.CANCELLED,
            )
        }
    }

    private fun setSuccessfulUploadingState() {
        _uiState.update {
            it.copy(
                uploadingState = FileUploadingState.COMPLETE,
                showErrorDialog = false,
                isUploadButtonEnabled = true,
            )
        }
    }
}

