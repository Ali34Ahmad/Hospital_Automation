package com.example.upload_employee_profile_image

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.constants.enums.FileUploadingState
import com.example.model.File
import com.example.network.remote.upload_employee_profile_image.UploadEmployeeProfileImageApi
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

class UploadEmployeeProfileImageViewModel(
    private val uploadProfileImageApi: UploadEmployeeProfileImageApi,
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

    private fun updateErrorState(error: Error?) {
        _uiState.update { it.copy(error = error) }
    }

    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateErrorDialogDescription(value: String) {
        _uiState.update { it.copy(errorDialogText = value) }
    }

    private fun uploadImage(uri: Uri) {
        uploadJob = uploadProfileImageApi
            .uploadImage(uri)
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
                        "Image is too big to be used in out system."
                    }

                    is FileNotFoundException -> {
                        "Image can not be found. Please try again."
                    }

                    is UnresolvedAddressException -> {
                        "Please check your internet connection and try again later."
                    }

                    else -> {
                        "Something went wrong. Please try again later.${cause.message}"
                    }
                }

                setFailureState(NetworkError.UNKNOWN,message)
                Log.v("Uploading Image:", "Exception")
            }
            .launchIn(viewModelScope)
    }

    private fun updateUploadingProgressState(uploadingProgress:Int,fileSize: Long) {
        _uiState.update {
            it.copy(
                imageFile = File(
                    uploadingProgress = uploadingProgress,
                    fileSizeWithBytes = fileSize,
                    fileName = "FileName"
                ),
                error = null,
                showErrorDialog = false,
                isLoading = true,
            )
        }
    }

    private fun setStartUploadingState(uri: Uri) {
        _uiState.update {
            it.copy(
                uri = uri,
                imageFile = File(
                    uploadingProgress = 0,
                    fileSizeWithBytes = 0,
                    fileName = "FileName"
                ),
                error = null,
                showErrorDialog = false,
                isLoading = true,
            )
        }
    }

    private fun setFailureState(error: Error?,message: String) {
        _uiState.update {
            it.copy(
                uri = null,
                imageFile = null,
                error = error,
                errorDialogText = message,
                showErrorDialog = true,
                isLoading = true,
            )
        }
    }

    private fun cancelUpload() {
        uploadJob?.cancel()
        setCancelUploadingState()
    }

    private fun setCancelUploadingState(){
        _uiState.update {
            it.copy(
                isLoading = false,
                uri = null,
                imageFile = null,
                error = null,
                showErrorDialog = false,
            )
        }
    }

    private fun setSuccessfulUploadingState(){
        _uiState.update { it.copy(loadingState = FileUploadingState.COMPLETE,
            isLoading = false,
            error = null,
            showErrorDialog = false,
            isUploadButtonEnabled = true,
            ) }
    }
}

