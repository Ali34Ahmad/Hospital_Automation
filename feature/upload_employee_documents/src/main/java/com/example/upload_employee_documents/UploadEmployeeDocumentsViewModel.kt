package com.example.upload_employee_documents

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.constants.enums.FileUploadingState
import com.example.model.File
import com.example.network.remote.upload_employee_documents.UploadEmployeeDocumentsApi
import com.example.utility.network.Error
import com.example.utility.network.NetworkError
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

class UploadEmployeeDocumentsViewModel(
    private val fileUploadService: UploadEmployeeDocumentsApi,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UploadEmployeeDocumentsUiState())
    val uiState = _uiState.asStateFlow()

    private var uploadJob: Job? = null

    fun getUiActions(
        navActions: UploadEmployeeDocumentsNavigationUiActions,
    ): UploadEmployeeDocumentsUiActions = UploadEmployeeDocumentsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): UploadEmployeeDocumentsBusinessUiActions =
        object : UploadEmployeeDocumentsBusinessUiActions {
            override fun onFileUploadingPause() {
                TODO("Not yet implemented")
            }

            override fun onFileUploadingOpen() {
                TODO("Not yet implemented")
            }

            override fun onFileUploadingResume() {
                TODO("Not yet implemented")
            }

            override fun onUploadFileButtonClick() {
                TODO("Not yet implemented")
            }

            override fun onUploadFile(uri: Uri) {
                uploadFile(uri)
            }

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onCancelFileUpload() {
                cancelUpload()
            }

        }

    private fun updateIsUploadingState(isUploadingLoading: Boolean) {
        _uiState.update { it.copy(isUploading = isUploadingLoading) }
    }

    private fun updateErrorState(error: Error?) {
        _uiState.update { it.copy(error = error) }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateErrorDialogDescription(value: String) {
        _uiState.update { it.copy(errorDialogText = value) }
    }

    fun uploadFile(uri: Uri) {
        uploadJob = fileUploadService
            .uploadFile(uri)
            .onStart {
                setStartUploadingState(uri)
                Log.v("Uploading File:","Started")
            }
            .onEach {
                updateUploadingProgressState(
                    uploadingProgress = ((it.bytesSent.toFloat() / it.totalBytes.toFloat()) * 100).toLong(),
                    fileSize = it.totalBytes,
                )
                Log.v("Uploading File:","New Emit")
            }
            .onCompletion { cause ->
                if (cause == null) {
                    setSuccessfulUploadingState()
                } else if (cause is CancellationException) {
                    setCancelUploadingState()
                }
                Log.v("Uploading File:","Complete $cause")
            }
            .catch { cause ->
                val message = when (cause) {
                    is OutOfMemoryError -> {
                        "File is too big to be used in out system."
                    }

                    is FileNotFoundException -> {
                        "File can not be found. Please try again."
                    }

                    is UnresolvedAddressException -> {
                        "Please check your internet connection and try again later."
                    }

                    else -> {
                        "Something went wrong. Please try again later.${cause.message}"
                    }
                }

                setFailureState()
                Log.v("Uploading File:","Exception")
            }
            .launchIn(viewModelScope)
    }

    private fun cancelUpload() {
        uploadJob?.cancel()
        _uiState.update {
            it.copy(
                fileUri = null,
                fileUiInfo = null,
                fileUploadingState = FileUploadingState.UPLOADING,
                isNextButtonEnabled = false,
                isUploading = false,
                error = null,
                showErrorDialog = false,
                errorDialogText = "",
                isUploadingComplete = false,
                isUploadingCancelled = true,
            )
        }
    }

    private fun setStartUploadingState(uri: Uri) {
        _uiState.update {
            it.copy(
                isUploading = true,
                isUploadingComplete = false,
                error = null,
                showErrorDialog = false,
                fileUri = uri,
                fileUploadingState = FileUploadingState.UPLOADING,
                fileUiInfo = File(
                    uploadingProgress = 0,
                    fileSizeWithBytes = 0,
                    fileName = "FileName"
                ),
            )
        }
    }

    private fun updateUploadingProgressState(uploadingProgress: Long, fileSize: Long) {
        _uiState.update {
            it.copy(
                fileUiInfo = uiState.value.fileUiInfo?.copy(
                    uploadingProgress = uploadingProgress.toInt(),
                    fileSizeWithBytes = fileSize,
                    fileName = "FileName",
                ),
            )
        }
    }

    private fun setSuccessfulUploadingState() {
        _uiState.update {
            it.copy(
                isUploading = false,
                isUploadingComplete = true,
                fileUploadingState = FileUploadingState.COMPLETE,
            )
        }
    }

    private fun setCancelUploadingState() {
        _uiState.update {
            it.copy(
                isUploading = false,
                isUploadingComplete = false,
                isUploadingCancelled = true,
                fileUploadingState = FileUploadingState.Cancelled,
                showErrorDialog = true,
                errorDialogText = "Cancelled uploading"
            )
        }
    }

    private fun setFailureState() {
        _uiState.update {
            it.copy(
                isUploading = false,
                isUploadingComplete = false,
                isUploadingCancelled = true,
                fileUploadingState = FileUploadingState.FAILED,
                showErrorDialog = true,
                error = NetworkError.UNKNOWN,
            )
        }
    }
}

