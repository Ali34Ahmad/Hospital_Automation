package com.example.children_search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.helper.ext.toAge
import com.example.ui_components.components.card.ChildCard
import com.example.model.Child
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.topbars.custom.ChildrenSearchBar

@Composable
fun ChildrenSearchScreen(
    viewModel: ChildrenSearchViewModel,
    onAction: (ChildrenSearchUIActions)-> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.uiState.collectAsState()
    val query = viewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface
            ){
                ChildrenSearchBar(
                    query = query.value,
                    onQueryChange = { newQuery ->
                        onAction(ChildrenSearchUIActions.OnSearchQueryChanged(newQuery))
                    },
                    onTrailingIconClick = {
                        onAction(ChildrenSearchUIActions.DeleteQuery)
                    },
                    onSearch = {
                        Log.d("Search Tag", "GuardiansSearchScreen: $it")
                    },
                    onNavigateUp = {
                        onAction(ChildrenSearchUIActions.NavigateBack)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = MaterialTheme.spacing.medium16,
                            top = MaterialTheme.spacing.medium16,
                            bottom = MaterialTheme.spacing.medium16,
                        ),
                )
            }
        }
    ) { innerPadding ->

        val children = viewModel.childrenFlow.collectAsLazyPagingItems()
        when(children.loadState.refresh){
            is LoadState.Error -> onAction(ChildrenSearchUIActions.ShowError)
            LoadState.Loading -> onAction(ChildrenSearchUIActions.OnSearch(true))
            is LoadState.NotLoading -> onAction(ChildrenSearchUIActions.OnSearch(true))
        }
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
                items(children.itemCount) { index ->
                    val childData = children[index]
                    childData?.let { data->
                        ChildCard(
                            child = Child(
                                id = data.id,
                                name = data.fullName,
                                age = data.age,
                                fatherName = data.fatherFullName,
                                motherName = data.motherFullName
                            ),
                            onClick = {
                                onAction(ChildrenSearchUIActions.NavigateToChildDetail(data.id))
                            },
                            modifier = Modifier
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

                // showing error card
                if(state.value.hasError){
                    item {
                        SomeThingWentWrong(
                            modifier = Modifier.padding(
                                vertical = MaterialTheme.spacing.large24,
                            )
                        )
                    }
                }

                if(children.loadState.append == LoadState.Loading){
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .padding(MaterialTheme.spacing.medium16),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun HelloPreview() {
    Hospital_AutomationTheme {
        Box(
            modifier = Modifier.size(MaterialTheme.spacing.large24)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}