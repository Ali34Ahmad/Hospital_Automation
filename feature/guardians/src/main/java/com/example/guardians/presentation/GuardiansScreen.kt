package com.example.guardians.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.list_items.SimpleProfileListItem
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.constants.icons.AppIcons
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn

@Composable
fun GuardiansScreen(
    viewModel: GuardiansViewModel,
    navigationAction: GuardianNavigationAction,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GuardiansScreen(
        uiState = uiState,
        navigationAction = navigationAction,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}
@Composable
internal fun GuardiansScreen(
    onAction:(GuardiansUIActions)-> Unit,
    uiState: GuardiansUIState,
    navigationAction: GuardianNavigationAction,
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
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HospitalAutomationTopBar(
                title = stringResource(R.string.guardians),
                onNavigationIconClick = {
                    navigationAction.navigateUp()
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = AppIcons.Outlined.arrowBack,
            )
        }
    ) {  innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
        ) {
            AnimatedContent(uiState.state) {state->
                when(state){
                    ScreenState.IDLE ->{}
                    ScreenState.LOADING ->{
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                        ){
                            FetchingDataItem()
                        }
                    }
                    ScreenState.ERROR -> {
                        PullToRefreshColumn(
                            refreshing = uiState.isRefreshing,
                            modifier = Modifier.fillMaxSize(),
                            onRefresh = {
                                onAction(GuardiansUIActions.Refresh)
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
                        if(uiState.data.isEmpty()){
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        GuardiansUIActions.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    title = stringResource(R.string.no_matching_result),
                                    description = stringResource(R.string.no_guardians)
                                )
                            }
                        }else{
                            PullToRefreshBox(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(GuardiansUIActions.Refresh)
                                },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                LazyColumn(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    items(uiState.data) { guardian ->
                                        SimpleProfileListItem(
                                            imageUrl = guardian.img,
                                            name = guardian.fullName,
                                            onClick = {
                                                navigationAction.navigateToGuardianProfile(guardian.id)
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
    }
}