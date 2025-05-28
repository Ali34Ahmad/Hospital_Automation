package com.example.guardians_search

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.list_items.GuardianListItem
import com.example.ui_components.components.topbars.custom.GuardianSearchBar

@Composable
fun GuardiansSearchScreen(
    viewModel: GuardiansSearchViewModel,
    onAction: (GuardiansSearchActions) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.uiState.collectAsState()
    val query = viewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface
            ) {
                GuardianSearchBar(
                    query = query.value,
                    onQueryChange = {
                        onAction(GuardiansSearchActions.OnQueryChange(it))
                    },
                    onTrailingIconClick = {
                        onAction(GuardiansSearchActions.OnDeleteQuery)
                    },
                    onSearch = {
                        Log.d("Search Tag", "GuardiansSearchScreen: $it")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = MaterialTheme.spacing.medium16,
                            top = MaterialTheme.spacing.medium16,
                            bottom = MaterialTheme.spacing.medium16,
                        ),
                    onNavigateUp = {
                        onAction(GuardiansSearchActions.OnNavigateBack)
                    },
                )
            }
        }
    ) {innerPadding->

        val guardians = viewModel.guardiansFlow.collectAsLazyPagingItems()

        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(MaterialTheme.sizing.small16)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                val numberOfGuardians = guardians.itemCount
                items(numberOfGuardians) { index ->
                    val guardianData = guardians[index]
                    guardianData?.let {
                        GuardianListItem(
                            onClick = { userId ->
                                onAction(GuardiansSearchActions.NavigateToGuardianDetails(id = userId))
                            },
                            imageUrl = it.img,
                            name = it.fullName,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.sizing.small8))
                    }
                }


                if (numberOfGuardians == 0) {
                    item {
                        Spacer(modifier = Modifier.height(MaterialTheme.sizing.small16))
                        Text(
                            text = "No results ${viewModel.searchQuery.collectAsState().value}"
                        )
                    }
                }

                //show circular progress indicator when fetching data
                if (state.value.isSearching && query.value.isNotBlank()) {
                    item {
                        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                        Text("searching for ${query.value}")
                        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                        CircularProgressIndicator()
                    }
                }
                guardians.loadState.apply {
                    when{
                        refresh is LoadState.Loading ->{onAction(GuardiansSearchActions.OnSearch(true))}
                        append is LoadState.Loading ->{onAction(GuardiansSearchActions.OnSearch(true))}
                        refresh is LoadState.Error ->{onAction(GuardiansSearchActions.ShowError)}
                        append is LoadState.Error ->{onAction(GuardiansSearchActions.ShowError)}
                    }
                }

                item {
                    AnimatedVisibility(state.value.hasError) {
                        SomeThingWentWrong(
                            modifier = Modifier.padding(
                                vertical = MaterialTheme.spacing.large24,
                            )
                        )
                    }

                }

            }
        }
    }
}

