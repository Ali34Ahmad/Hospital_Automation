package com.example.guardians_search.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.enums.ScreenState
import com.example.model.guardian.GuardianData
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.custom.CenteredMessage
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.list_items.GuardianListItem
import com.example.ui_components.components.topbars.custom.GuardianSearchBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn

@Composable
fun GuardiansSearchScreen(
    viewModel: GuardiansSearchViewModel,
    navigationActions: GuardiansSearchNavigationActions,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val guardians = remember(uiState.searchQuery) {
        if(uiState.searchQuery.isBlank()) null else viewModel.guardiansFlow
    }?.collectAsLazyPagingItems()



    GuardiansSearchScreen(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        guardians = guardians
    )
}

@Composable
fun GuardiansSearchScreen(
    uiState: GuardiansSearchUiState,
    onAction: (GuardiansSearchActions)-> Unit,
    navigationActions: GuardiansSearchNavigationActions,
    guardians: LazyPagingItems<GuardianData>?,
    modifier: Modifier = Modifier
){

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
                GuardianSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = {
                       onAction(GuardiansSearchActions.OnQueryChange(it))
                    },
                    onTrailingIconClick = {
                       onAction(GuardiansSearchActions.OnDeleteQuery)
                    },
                    onSearch = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = MaterialTheme.spacing.medium16,
                            top = MaterialTheme.spacing.medium16,
                            bottom = MaterialTheme.spacing.medium16,
                        ),
                    onNavigateUp = {
                        navigationActions.navigateUp()
                    },
                )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) {innerPadding->

        guardians?.let {
            //observing the load state changes
            when (it.loadState.refresh) {
                is LoadState.Loading -> {
                    onAction(GuardiansSearchActions.UpdateFetchingDataState(ScreenState.LOADING))
                }

                is LoadState.Error -> {
                    onAction(GuardiansSearchActions.UpdateFetchingDataState(ScreenState.ERROR))
                }

                is LoadState.NotLoading -> {
                    onAction(GuardiansSearchActions.UpdateFetchingDataState(ScreenState.SUCCESS))
                }
            }
        }

        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            AnimatedContent(uiState.screenState) {state->
                when(state){
                    ScreenState.IDLE -> {
                        CenteredMessage(
                            modifier = Modifier.fillMaxSize(),
                            title = stringResource(R.string.search_by_name_title),
                            subtitle = stringResource(R.string.search_by_name_subtitle)
                        )
                    }
                    ScreenState.LOADING -> {
                        FetchingDataItem(Modifier.fillMaxSize())
                    }
                    ScreenState.ERROR ->{
                        PullToRefreshColumn(
                            refreshing = uiState.isRefreshing,
                            modifier = Modifier.fillMaxSize(),
                            onRefresh = {
                                onAction(GuardiansSearchActions.Refresh)
                            },
                            verticalArrangement = Arrangement.Center
                        ) {
                            ErrorComponent(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.medium16)
                            )
                        }
                    }
                    ScreenState.SUCCESS -> guardians?.let{

                        val numberOfGuardians = guardians.itemCount
                        //if no items display this composable.
                        if(numberOfGuardians == 0){

                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        GuardiansSearchActions.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {

                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16),
                                    title = stringResource(R.string.no_matching_result),
                                    description = stringResource(R.string.no_children_subtitle)
                                )
                            }
                        }else{
                            PullToRefreshBox(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        GuardiansSearchActions.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(MaterialTheme.spacing.medium16),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                ) {
                                    items(numberOfGuardians) { index ->
                                        val guardianData = guardians[index]
                                        guardianData?.let {
                                            GuardianListItem(
                                                onClick = {
                                                    navigationActions.navigateToGuardianDetails(
                                                        it.id,
                                                        uiState.childId
                                                    )
                                                },
                                                imageUrl = it.img,
                                                name = it.fullName,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                            Spacer(modifier = Modifier.height(MaterialTheme.sizing.small8))
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
