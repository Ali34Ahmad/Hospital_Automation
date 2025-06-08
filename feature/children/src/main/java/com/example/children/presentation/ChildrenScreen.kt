package com.example.children.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.Child
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.components.card.ChildCard
import com.example.ui_components.components.items.custom.CenteredMessage
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
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
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                        ){
                            FetchingDataItem()
                        }
                    }
                    ScreenState.ERROR -> {
                        SomeThingWentWrong(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    ScreenState.SUCCESS -> {
                        if(uiState.userChildren.isEmpty()) {
                            CenteredMessage(
                                modifier = Modifier.fillMaxSize()
                            )
                        }else{
                            LazyColumn(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {


                                items(uiState.userChildren, key = { child-> child.childId?:0 }) { child ->
                                    child.apply {
                                        ChildCard(
                                            child = Child(
                                                id = childId?:0,
                                                name = fullName,
                                                age = age,
                                                fatherName = fatherFullName,
                                                motherName = motherFullName
                                            ),
                                            onClick = {
                                                navigationActions.navigateToChildProfile(childId?:0)
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


