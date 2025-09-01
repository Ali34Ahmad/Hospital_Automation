package com.example.children.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.Child
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.ChildCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.items.custom.CenteredMessage
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.custom.ChildrenTopBar

@Composable
fun ChildrenScreen(
    viewModel: ChildrenViewModel,
    navigationActions: ChildrenNavigationAction,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ChildrenScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier
    )
}

@Composable
internal fun ChildrenScreen(
    uiState: ChildrenUIState,
    navigationActions: ChildrenNavigationAction,
    onAction:(ChildrenUIAction)-> Unit,
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
            ChildrenTopBar(
                onNavigateUp = navigationActions::navigateUp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
        ) {
            AnimatedContent(uiState.screenState) { state->
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
                                onAction(ChildrenUIAction.Refresh)
                            },
                            verticalArrangement = Arrangement.Center
                        ){
                            ErrorComponent(
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                    ScreenState.SUCCESS -> {
                        if(uiState.userChildren.isEmpty()) {
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        ChildrenUIAction.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    title = stringResource(R.string.no_matching_result),
                                    description = stringResource(R.string.no_items_subtitle)
                                )
                            }
                        }else{
                            PullToRefreshBox(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(ChildrenUIAction.Refresh)
                                },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                LazyColumn(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    items(
                                        uiState.userChildren,
                                        key = { child -> child.childId ?: 0 }) { child ->
                                        child.apply {
                                            Box {
                                                ChildCard(
                                                    child = Child(
                                                        id = childId ?: 0,
                                                        name = fullName,
                                                        age = age,
                                                        fatherName = fatherFullName,
                                                        motherName = motherFullName
                                                    ),
                                                    onClick = {
                                                        navigationActions.navigateToChildProfile(
                                                            childId ?: 0
                                                        )
                                                    },
                                                    modifier = Modifier.fillMaxSize()
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
    }
}


