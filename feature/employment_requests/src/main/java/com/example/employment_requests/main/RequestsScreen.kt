package com.example.employment_requests.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.constants.icons.AppIcons
import com.example.ext.toAppropriateNameFormat
import com.example.model.enums.ScreenState
import com.example.model.work_request.RequestState
import com.example.model.work_request.RequestType
import com.example.model.work_request.SingleRequestResponse
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.IllustrationCard
import com.example.ui_components.components.card.RequestCard
import com.example.ui_components.components.progress_indicator.SmallCircularProgressIndicator
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun RequestsScreen(
    uiState: RequestsUiState,
    uiActions: RequestsUiActions,
    requests: LazyPagingItems<SingleRequestResponse>,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let {
            Toast.makeText(
                context,
                it.asString(context),
                Toast.LENGTH_SHORT
            ).show()
            uiActions.clearToastMessage()
        }
    }
    LaunchedEffect(requests.loadState.refresh) {
        requests.let {
            when (it.loadState.refresh) {
                is LoadState.Error -> {
                    Log.d("GetRequestsViewModel", "Error ")
                    uiActions.onUpdateScreenState(ScreenState.ERROR)
                }

                LoadState.Loading -> {
                    Log.d("GetRequestsViewModel", "Loading ")
                    uiActions.onUpdateScreenState(ScreenState.LOADING)
                }

                is LoadState.NotLoading -> {
                    Log.d("GetRequestsViewModel", "Not loading ")
                    uiActions.onUpdateScreenState(ScreenState.SUCCESS)
                }
            }
        }
    }

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            HospitalAutomationTopBar(
                title = stringResource(R.string.requests),
                onNavigationIconClick = { TODO() },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.openDrawer,
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {

            if (uiState.screenState == ScreenState.LOADING) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(MaterialTheme.sizing.circularProgressIndicatorSize)
                    )
                }
            } else if (uiState.screenState == ScreenState.ERROR) {
                PullToRefreshBox(
                    refreshing = uiState.isRefreshing,
                    onRefresh = { uiActions.onRefresh() }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        IllustrationCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MaterialTheme.spacing.medium16),
                            illustration = {
                                Image(
                                    painter = painterResource(R.drawable.ill_error),
                                    contentDescription = null,
                                    modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
                                )
                            },
                            title = stringResource(R.string.network_error),
                            description = stringResource(R.string.something_went_wrong),
                        )
                    }
                }
            }
            if (uiState.screenState == ScreenState.SUCCESS) {
                PullToRefreshBox(
                    refreshing = uiState.isRefreshing,
                    onRefresh = { uiActions.onRefresh() }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                        contentPadding = PaddingValues(
                            vertical = MaterialTheme.spacing.medium16,
                            horizontal = MaterialTheme.spacing.medium16,
                        )
                    ) {
                        items(requests.itemCount) { index ->
                            val request = requests[index]
                            when (request) {
                                null -> Unit
                                else -> RequestCard(
                                    onClick = { requestType ->
                                        when(requestType){
                                            RequestType.EMPLOYEE->uiActions.navigateToEmployeeProfileDetailsScreen(request.userMainInfo.id)
                                            RequestType.DOCTOR->uiActions.navigateToDoctorProfileDetailsScreen(request.userMainInfo.id)
                                            RequestType.PHARMACIST->uiActions.navigateToPharmacyDetailsScreen(request.pharmacyId)
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    requestType = request.requestType,
                                    requestId = request.id,
                                    clinicMainInfo = request.clinicMainInfo,
                                    userProfileImageUrl = request.userMainInfo.imageUrl ?: "",
                                    username = request.userMainInfo.fullName.toAppropriateNameFormat(),
                                    onAcceptButton = { requestId ->
                                        uiActions.onChangeRequestState(
                                            requestId,
                                            RequestState.ACCEPTED
                                        )
                                    },
                                    onRejectButton = { requestId ->
                                        uiActions.onChangeRequestState(
                                            requestId,
                                            RequestState.REJECTED
                                        )
                                    },
                                    onProfileItemClick = {},
                                    requestState = request.state,
                                )
                            }
                        }
                        if (requests.loadState.append == LoadState.Loading) {
                            item {
                                SmallCircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}