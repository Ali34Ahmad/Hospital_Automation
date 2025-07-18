package com.example.pharmacies.presentaion

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.constants.icons.AppIcons
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.list_items.PharmacyListItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.HospitalAutomationTopBar

@Composable
fun PharmaciesScreen(
    viewModel: PharmaciesViewModel,
    navigationActions: PharmaciesNavigationActions,
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.uiState.collectAsState()
    PharmaciesScreen(
        onAction = viewModel::onAction,
        uiState = uiState.value,
        navigationActions = navigationActions,
        modifier = modifier,
    )
}

@Composable
fun PharmaciesScreen(
    onAction: (PharmaciesUIAction)-> Unit,
    uiState: PharmaciesUIState,
    navigationActions: PharmaciesNavigationActions,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val toastMessage = uiState.toastMessage?.asString()
    LaunchedEffect(toastMessage) {
        if (toastMessage != null){
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HospitalAutomationTopBar(
                title = uiState.medicineName,
                onNavigationIconClick = navigationActions::navigateBack,
                navigationIcon = AppIcons.Outlined.arrowBack,
            )
        }
    ) { innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
        ) {
            AnimatedContent(uiState.state) { state ->
                when (state) {
                    ScreenState.IDLE -> Unit
                    ScreenState.LOADING -> FetchingDataItem(
                        Modifier.fillMaxSize()
                    )

                    ScreenState.ERROR -> PullToRefreshColumn(
                        refreshing = uiState.isRefreshing,
                        modifier = Modifier.fillMaxSize(),
                        onRefresh = {
                            onAction(PharmaciesUIAction.Refresh)
                        },
                        verticalArrangement = Arrangement.Center
                    ) {
                        ErrorComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    ScreenState.SUCCESS -> {
                        if (uiState.data.isEmpty()) {
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        PharmaciesUIAction.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    title = stringResource(R.string.no_matching_result),
                                    description = stringResource(R.string.no_pharmacies)
                                )
                            }
                        }
                        else{
                            PullToRefreshBox(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(PharmaciesUIAction.Refresh)
                                },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                LazyColumn(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    items(uiState.data, key = {it.pharmacyId}) { pharmacy ->
                                        PharmacyListItem(
                                            imageUrl = pharmacy.pharmacist.imageUrl,
                                            pharmacyName = pharmacy.name,
                                            pharmacistName =pharmacy.pharmacist.fullName,
                                            onClick = {
                                                navigationActions.navigateToPharmacyDetails(
                                                    pharmacy.pharmacyId
                                                )
                                            },
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}