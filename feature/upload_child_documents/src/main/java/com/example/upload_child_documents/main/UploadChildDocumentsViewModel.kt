package com.example.upload_child_documents.main

import android.net.Uri
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.constants.enums.FileUploadingState
import com.example.domain.use_cases.upload_child_documents.UploadChildDocumentsUseCase
import com.example.model.FileInfo
import com.example.ui_components.R
import com.example.upload_child_documents.navigation.UploadChildDocumentsRoute
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
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.nio.channels.UnresolvedAddressException

class UploadChildDocumentsViewModel(
    private val uploadChildDocumentsUseCase: UploadChildDocumentsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UploadChildDocumentsUiState())
    val uiState = _uiState.asStateFlow()

    private var uploadJob: Job? = null

    init {
        val routeArgs=savedStateHandle.toRoute<UploadChildDocumentsRoute>()
        _uiState.update {
            it.copy(childId=routeArgs.id)
        }
    }

    fun getUiActions(
        navActions: UploadChildDocumentsNavigationUiActions,
    ): UploadChildDocumentsUiActions = UploadChildDocumentsUiActions(
        navigationActions = navActions,
        businessActions = getBusinessUiActions()
    )

    private fun getBusinessUiActions(): UploadChildDocumentsBusinessUiActions =
        object : UploadChildDocumentsBusinessUiActions {
            override fun onFileUploadingPause() {

            }

            override fun onFileUploadingOpen() {

            }

            override fun onFileUploadingResume() {

            }

            override fun onUploadFileButtonClick() {

            }

            override fun onUploadFile(uri: Uri) {
                uploadFile(uri)
            }

            override fun showSuccessCard() {
                updateShowSuccessCard(true)
            }

            override fun onShowErrorDialogStateChange(value: Boolean) {
                updateShowErrorDialogState(value)
            }

            override fun onCancelFileUpload() {
                cancelUpload()
            }

        }
    private fun updateShowErrorDialogState(isShown: Boolean) {
        _uiState.update { it.copy(showErrorDialog = isShown) }
    }

    private fun updateShowSuccessCard(value: Boolean) {
        _uiState.update { it.copy(showSuccessCard = value) }
    }

    fun uploadFile(uri: Uri) {
        viewModelScope.launch{
            val childId = uiState.value.childId
            if (childId == null) return@launch
            uploadJob = uploadChildDocumentsUseCase(uri, childId)
                .onStart {
                    setStartUploadingState(uri)
                    Log.v("Uploading File:", "Started")
                }
                .onEach {
                    updateUploadingProgressState(
                        uploadingProgress = ((it.bytesSent.toFloat() / it.totalBytes.toFloat()) * 100).toLong(),
                        fileSize = it.totalBytes,
                    )
                    Log.v("Uploading File:", "New Emit")
                }
                .onCompletion { cause ->
                    if (cause == null) {
                        setSuccessfulUploadingState()
                    } else if (cause is CancellationException) {
                        setCancelUploadingState()
                    }
                    Log.v("Uploading File:", "Complete $cause")
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
                    Log.v("Uploading File:", cause.message ?: "Unknown Error")
                }
                .launchIn(viewModelScope)
        }
    }

    private fun cancelUpload() {
        uploadJob?.cancel()
        _uiState.update {
            it.copy(
                fileUri = null,
                fileInfo = null,
                fileUploadingState = FileUploadingState.UPLOADING,
                isNextButtonEnabled = false,
                showErrorDialog = false,
                errorDialogText = null,
            )
        }
    }

    private fun setStartUploadingState(uri: Uri) {
        _uiState.update {
            it.copy(
                showErrorDialog = false,
                fileUri = uri,
                fileUploadingState = FileUploadingState.UPLOADING,
                fileInfo = FileInfo(
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
                fileInfo = uiState.value.fileInfo?.copy(
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
                fileUploadingState = FileUploadingState.COMPLETE,
            )
        }
    }

    private fun setCancelUploadingState() {
        _uiState.update {
            it.copy(
                fileUploadingState = FileUploadingState.CANCELLED,
                showErrorDialog = false,
                errorDialogText = UiText.StringResource(R.string.uploading_cancelled)
            )
        }
    }

    private fun setFailureState(message:UiText?) {
        _uiState.update {
            it.copy(
                fileUploadingState = FileUploadingState.FAILED,
                showErrorDialog = true,
                errorDialogText = message
            )
        }
    }
}

