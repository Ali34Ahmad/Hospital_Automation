package com.example.guardians_search.presentation

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
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
import com.example.ui_components.components.items.custom.EmptyResult
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.list_items.GuardianListItem
import com.example.ui_components.components.topbars.custom.GuardianSearchBar
import androidx.compose.runtime.getValue

@Composable
fun GuardiansSearchScreen(
    viewModel: GuardiansSearchViewModel,
    onAction: (GuardiansSearchActions) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    val guardians = viewModel.guardiansFlow.collectAsLazyPagingItems()
    GuardiansSearchScreen(
        modifier = modifier,
        uiState = uiState,
        onNavigateToGuardianDetails = { guardianId,childId->
            onAction(GuardiansSearchActions.NavigateToGuardianDetails(guardianId,childId))
        },
        onQueryChange = {newQuery->
            onAction(GuardiansSearchActions.OnQueryChange(newQuery))
        },
        onStateUpdated = {newQuery->
            onAction(GuardiansSearchActions.UpdateFetchingDataState(newQuery))
        },
        onNavigateUp = {
            onAction(GuardiansSearchActions.OnNavigateBack)
        },
        onQueryDeleted = {
            onAction(GuardiansSearchActions.OnDeleteQuery)
        },
        guardians = guardians
    )
}
@Composable
fun GuardiansSearchScreen(
    uiState: GuardiansSearchUiState,
    onQueryChange: (String)-> Unit,
    onQueryDeleted: ()-> Unit,
    onNavigateUp : () -> Unit,
    guardians: LazyPagingItems<GuardianData>,
    onStateUpdated: (ScreenState)-> Unit,
    onNavigateToGuardianDetails: (guardianId:Int,childId: Int?) -> Unit,
    modifier: Modifier = Modifier
){

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
                GuardianSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = {
                       onQueryChange(it)
                    },
                    onTrailingIconClick = {
                        onQueryDeleted()
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
                        onNavigateUp()
                    },
                )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) {innerPadding->


        //observing the load state changes
        when (guardians.loadState.refresh) {
            is LoadState.Loading -> {
                onStateUpdated(ScreenState.LOADING)
            }

            is LoadState.Error -> {
                onStateUpdated(ScreenState.ERROR)
            }

            is LoadState.NotLoading -> {
                onStateUpdated(ScreenState.Success)
            }
        }

        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when(uiState.screenState){
                ScreenState.IDLE -> Unit
                ScreenState.LOADING -> {
                        Surface (
                            modifier = Modifier.fillMaxSize()
                                .padding(MaterialTheme.spacing.medium16)
                        ){
                            FetchingDataItem()
                        }

                }
                ScreenState.ERROR ->{
                    Surface(
                        modifier = Modifier.fillMaxWidth()
                            .padding(MaterialTheme.spacing.medium16)
                    ) {
                        SomeThingWentWrong()
                    }
                }
                ScreenState.Success ->{

                    val numberOfGuardians = guardians.itemCount
                    //if no items display this composable.
                    if(numberOfGuardians == 0){

                            EmptyResult(
                                modifier = Modifier.fillMaxSize(),
                                title = R.string.no_items,
                                subtitle = R.string.no_items_subtitle
                            )
                    }else{
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(MaterialTheme.spacing.medium16),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                        ) {
                            items(numberOfGuardians) { index ->
                                val guardianData = guardians[index]
                                guardianData?.let{
                                    GuardianListItem(
                                        onClick = {guardianId->
                                            onNavigateToGuardianDetails(guardianId,uiState.childId)
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
