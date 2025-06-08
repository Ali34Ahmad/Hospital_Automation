package com.example.children_search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.Child
import com.example.model.child.ChildData
import com.example.model.enums.ScreenState
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.components.card.ChildCard
import com.example.ui_components.components.items.custom.CenteredMessage
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.topbars.custom.ChildrenSearchBar
import kotlin.Unit
import com.example.ui_components.R

@Composable
fun ChildrenSearchScreen(
    viewModel: ChildrenSearchViewModel,
    onAction: (ChildrenSearchUIActions)-> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val children = remember(uiState.query) {
        if(uiState.query.isBlank()) null else viewModel.childrenFlow
    }?.collectAsLazyPagingItems()
    ChildrenSearchScreen(
        modifier = modifier,
        uiState = uiState,
        children= children,
    onNavigateBack= {
        onAction(ChildrenSearchUIActions.NavigateBack)
    },
    onNavigateToChildProfile = {
        onAction(ChildrenSearchUIActions.NavigateToChildDetail(it))
    },
    onQueryChanged = {
        onAction(ChildrenSearchUIActions.OnQueryChanged(it))
    },
    onQueryDeleted={
        onAction(ChildrenSearchUIActions.DeleteQuery)
    },
    onStateUpdated = {
        onAction(ChildrenSearchUIActions.UpdateState(it))
    },
    )
}

@Composable
fun ChildrenSearchScreen(
    uiState: ChildrenSearchUiState,
    children: LazyPagingItems<ChildData>?,
    onNavigateBack: () -> Unit,
    onNavigateToChildProfile: (childId: Int)-> Unit,
    onQueryChanged: (String)-> Unit,
    onQueryDeleted: ()-> Unit,
    onStateUpdated: (ScreenState)-> Unit,
    modifier: Modifier = Modifier,
) {
    //observing the load state changes
    children?.let {
        when(it.loadState.refresh){
            is LoadState.Error -> onStateUpdated(ScreenState.ERROR)
            LoadState.Loading -> onStateUpdated(ScreenState.LOADING)
            is LoadState.NotLoading -> onStateUpdated(ScreenState.SUCCESS)
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            ChildrenSearchBar(
                    query = uiState.query,
                    onQueryChange = { newQuery ->
                        onQueryChanged(newQuery)
                    },
                    onTrailingIconClick = {
                        onQueryDeleted()
                    },
                    onSearch = {},
                    onNavigateUp = {
                        onNavigateBack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.spacing.medium16
                        ),
                )
        },
        modifier = modifier
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.sizing.small16)
        ) {
            when(uiState.state){
                ScreenState.IDLE -> {
                    CenteredMessage(
                        modifier = Modifier.fillMaxSize(),
                        title = stringResource(R.string.search_by_name_title),
                        subtitle = stringResource(R.string.search_by_name_subtitle)
                    )
                }
                ScreenState.LOADING ->{
                    FetchingDataItem(Modifier.fillMaxSize())
                }
                ScreenState.ERROR ->{
                    SomeThingWentWrong(Modifier.fillMaxWidth())
                }
                ScreenState.SUCCESS ->children?.let{ children->
                    val count = children.itemCount
                    if(count == 0) {
                        CenteredMessage(
                            modifier = Modifier.fillMaxSize()
                                .padding(MaterialTheme.spacing.small8),
                            title = stringResource(R.string.no_matching_result),
                            subtitle = stringResource(R.string.no_children_subtitle),
                        )
                    }else {
                        LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                    ) {
                        items(count) { index ->
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
                                        onNavigateToChildProfile(data.id)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                        if(children.loadState.append == LoadState.Loading){
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
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
        }
    }
}
