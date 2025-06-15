package com.example.child_profile.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ext.toDate
import com.example.ext.toGender
import com.example.model.FileInfo
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.buttons.custom.AddGuardianButton
import com.example.ui_components.components.card.ChildProfileCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.dialog.FileDownloaderDialog
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.custom.ChildProfileTopBar

@Composable
fun ChildProfileScreen(
    viewModel: ChildProfileViewModel,
    navigationActions: ChildProfileNavigationAction,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ChildProfileScreen(
        modifier = modifier,
        uiState = uiState.value,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
    )
}

@Composable
fun ChildProfileScreen(
    uiState: ChildProfileUIState,
    onAction:(ChildProfileUIAction)-> Unit,
    navigationActions: ChildProfileNavigationAction,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
//    val toastMessage = uiState.toastMessage
//    LaunchedEffect(toastMessage) {
//        if(toastMessage != null){
//            Toast.makeText(context, toastMessage.asString(context), Toast.LENGTH_SHORT).show()
//        }
//    }

    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let {
            Toast.makeText(
                context,
                it.asString(context),
                Toast.LENGTH_SHORT
            ).show()
            onAction(ChildProfileUIAction.ClearToastMessage)
        }
    }

    FileDownloaderDialog(
        showDialog = uiState.showFileDownloaderDialog,
        onDismiss = {
            onAction(ChildProfileUIAction.HideFileDownloaderDialog)
        },
        userFullName = uiState.child?.fullName?:"",
        fileDownloadingState = uiState.fileDownloadingState,
        fileInfo = uiState.fileInfo ?: FileInfo(0, 0, "NULL"),
        profileImageUrl = uiState.child?.birthCertificateImgUrl?: "",
        onDownload = {
            onAction(ChildProfileUIAction.DownloadFile)
        },
        onCancelDownload = {
            onAction(ChildProfileUIAction.CancelFileDownloading)
        }
    )

    val child = uiState.child
    var name = ""
    child?.let {
        name = "${it.firstName} ${it.lastName}"
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            ChildProfileTopBar(
                modifier = Modifier.fillMaxWidth() ,
                name = name,
                onNavigateUpClick = navigationActions::navigateUp,
            )
        },
        bottomBar = {
            AddGuardianButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16),
                onClick = {
                    child?.childId?.let {
                        navigationActions.navigateToAddGuardianScreen(it)
                    }
                },
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium16)
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = uiState.state
            ) {state->
                when(state){
                    ScreenState.IDLE ->{}
                    ScreenState.LOADING ->{
                        FetchingDataItem(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    ScreenState.ERROR -> {
                        PullToRefreshColumn(
                            refreshing = uiState.isRefreshing,
                            modifier = Modifier.fillMaxSize(),
                            onRefresh = {
                                onAction(ChildProfileUIAction.Refresh)
                            },
                            verticalArrangement = Arrangement.Center
                        ){
                            ErrorComponent(
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                    }
                    }
                    ScreenState.SUCCESS ->{
                        child?.let { child
                        PullToRefreshColumn(
                            refreshing = uiState.isRefreshing,
                            onRefresh = {
                                onAction(ChildProfileUIAction.Refresh)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ){
                                ChildProfileCard(
                                    fatherName = child.fatherLastName,
                                    motherName = child.motherLastName,
                                    gender = child.gender.toGender(),
                                    dateOfBirth = child.dateOfBirth.toDate(),
                                    employeeName = child.employeeName
                                        ?: stringResource(R.string.not_provided),
                                    guardiansNumber = child.numberOfGuardians ?: 1,
                                    onBirthCertificateItemClick = {
                                        onAction(ChildProfileUIAction.ShowFileDownloaderDialog)
                                    },
                                    onBirthCertificateItemDescriptionClick = {
                                        child.employeeId?.let {
                                            navigationActions.navigateToEmployeeProfileScreen(it)
                                        }
                                    },
                                    onGuardianTagItemClick = {
                                        child.childId?.let {
                                            navigationActions.navigateToGuardiansScreen(it)
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                        }
                    }
                }

            }


        }
    }

}

