package com.example.child_profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.child_profile.navigation.ChildProfileRoute
import com.example.domain.use_cases.children.GetChildByIdUseCase
import com.example.domain.use_cases.download_file.CancelFileDownloadUseCase
import com.example.domain.use_cases.download_file.DownloadFileUseCase
import com.example.domain.use_cases.download_file.ObserveFileDownloadProgressUseCase
import com.example.model.FileInfo
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.download_file.DownloadProgress
import com.example.model.enums.FileDownloadingState
import com.example.model.enums.ScreenState
import com.example.util.UiText
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.ui_components.R
import com.example.utility.url.getFileNameFromUrl
import kotlinx.coroutines.flow.update

class ChildProfileViewModel (
    private val getChildByIdUseCase: GetChildByIdUseCase,
    private val cancelFileDownloadUseCase: CancelFileDownloadUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val observeFileDownloadProgressUseCase: ObserveFileDownloadProgressUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val route = savedStateHandle.toRoute<ChildProfileRoute>()
    private val _uiState = MutableStateFlow(
        ChildProfileUIState(
            childId = route.childId,
            hasAdminAccess = route.hasAdminAccess
        )
    )
    val uiState: StateFlow<ChildProfileUIState> = _uiState
        .onStart {
            loadData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = _uiState.value
        )

    private fun loadData() {
        viewModelScope.launch{
            updateScreenState(ScreenState.LOADING)
            getChildByIdUseCase(uiState.value.childId)
                .onSuccess{ result->
                    updateChild(result)
                    updateScreenState(ScreenState.SUCCESS)
                }.onError {
                    updateScreenState(ScreenState.ERROR)
                }
        }
    }

    fun onAction(action: ChildProfileUIAction){
        when(action){

            is ChildProfileUIAction.UpdateChild ->{
                updateChild(action.child)
            }
            is ChildProfileUIAction.UpdateState ->{
                updateScreenState(action.newState)
            }

            is ChildProfileUIAction.UpdateRefreshState -> {
                updateRefreshState(action.isRefreshing)
            }

            ChildProfileUIAction.Refresh -> {
                refreshData()
            }
            is ChildProfileUIAction.ShowToast ->{
                showToast(action.message)
            }

            ChildProfileUIAction.CancelFileDownloading -> {
                cancelFileDownloading()
            }
            ChildProfileUIAction.ClearToastMessage -> {
                showToast(null)
            }
            ChildProfileUIAction.DownloadFile -> {
                downloadFile()
            }
            ChildProfileUIAction.HideFileDownloaderDialog -> {
                uploadFileDownloaderDialogVisibility(false)
            }
            ChildProfileUIAction.ShowFileDownloaderDialog -> {
                uploadFileDownloaderDialogVisibility(true)
            }
        }
    }
    private fun updateScreenState(newState: ScreenState) {
        _uiState.value = _uiState.value.copy(state = newState)
    }

    private fun showToast(message: UiText?) {
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }

    private fun updateRefreshState(isRefreshing: Boolean) {
        _uiState.value = _uiState.value.copy(isRefreshing = isRefreshing)
    }

    private fun updateChild(child: ChildFullData) {
        _uiState.value = _uiState.value.copy(child = child)

        val url = uiState.value.child?.birthCertificateImgUrl
        val fileInfo = uiState.value.child?.let {
            FileInfo(
                fileName = getFileNameFromUrl(url) ?: "",
                fileSizeWithBytes = 0L,
                uploadingProgress = 0,
            )
        }
        updateFileInfo(
            fileInfo =fileInfo
        )
    }

    private fun updateFileInfo(fileInfo: FileInfo?) {
        _uiState.update {
            it.copy(
                fileInfo = fileInfo
            )
        }
    }

    private fun refreshData(){
        viewModelScope.launch{
            updateRefreshState(true)
            getChildByIdUseCase(uiState.value.childId)
                .onSuccess{ result->
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    updateChild(result)
                    if(uiState.value.state == ScreenState.ERROR){
                        updateScreenState(ScreenState.SUCCESS)
                    }
                }.onError {
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }

    private fun downloadFile(){
        viewModelScope.launch {
            val fileUrl = uiState.value.child?.birthCertificateImgUrl
            if (fileUrl == null) return@launch
            val downloadId = downloadFileUseCase(url = fileUrl)
            if (downloadId == -1L) return@launch

            observeFileDownloadProgressUseCase(downloadId)
                .collect { downloadProgress ->
                    updateDownloadProgress(
                        downloadProgress
                    )
                }
        }
    }

    private fun updateDownloadProgress(downloadProgress: DownloadProgress) {
        _uiState.update {
            it.copy(
                downloadProgress = downloadProgress,
                fileInfo = uiState.value.fileInfo?.copy(
                    uploadingProgress = downloadProgress.progressPercent,
                ),
                fileDownloadingState = downloadProgress.fileDownloadingState,
            )
        }
    }

    private fun cancelFileDownloading(){
        val downloadId = uiState.value.downloadProgress?.downloadId
        if (downloadId == -1L || downloadId == null) return
        val removedDownload = cancelFileDownloadUseCase(downloadId)
        if (removedDownload > 0) {
            updateFileDownloadingState(FileDownloadingState.CANCELLED)
        }
    }

    private fun updateFileDownloadingState(fileDownloadingState: FileDownloadingState){
        _uiState.update {
            it.copy(fileDownloadingState = fileDownloadingState)
        }
    }

    private fun uploadFileDownloaderDialogVisibility(isVisible: Boolean){
        _uiState.update { it.copy(showFileDownloaderDialog = isVisible) }
    }
}

