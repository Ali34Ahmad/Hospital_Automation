package com.example.children.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.model.Child
import com.example.model.child.ChildData
import com.example.model.child.ChildFullData
import com.example.model.enums.FetchingDataState
import com.example.model.helper.IdType
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.components.card.ChildCard
import com.example.ui_components.components.items.custom.EmptyResult
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.topbars.custom.ChildrenTopBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ChildrenScreen(
    viewModel: ChildrenViewModel,
    onAction: (ChildrenUIAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val employeeChildren = viewModel.employeeChildren
    ChildrenScreen(
        uiState = uiState,
        onNavigateUp = {
            onAction(
                ChildrenUIAction.NavigateUp
            )
        },
        updateFetchingDataState = {
            onAction(ChildrenUIAction.UpdateFetchingDataState(it))
        },
        onNavigateToChildProfile = {
            onAction(ChildrenUIAction.NavigateToChildProfile(it))
        },
        employeeChildrenFlow = employeeChildren,
        modifier = modifier
    )
}

@Composable
fun ChildrenScreen(
    uiState: ChildrenUIState,
    onNavigateUp: () -> Unit,
    employeeChildrenFlow: Flow<PagingData<ChildData>>,
    onNavigateToChildProfile: (id:Int) -> Unit,
    updateFetchingDataState:(FetchingDataState)-> Unit,
    modifier: Modifier = Modifier
) {
    val employeeChildren = employeeChildrenFlow.collectAsLazyPagingItems()
    val loadState = employeeChildren.loadState
    //react to the loading state of the paged data
    when(loadState.refresh){
        is LoadState.Error -> {
            updateFetchingDataState(FetchingDataState.ERROR)
        }
        LoadState.Loading -> {
            updateFetchingDataState(FetchingDataState.LOADING)
        }
        is LoadState.NotLoading ->{
            updateFetchingDataState(FetchingDataState.Success)
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            ChildrenTopBar(
                onNavigateUp = onNavigateUp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16)
        ) {
            when(uiState.fetchingDataState){
                FetchingDataState.IDLE ->{}
                FetchingDataState.LOADING ->{
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ){
                        FetchingDataItem()
                    }
                }
                FetchingDataState.ERROR -> {
                    SomeThingWentWrong(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                FetchingDataState.Success -> {
                    if(uiState.userChildren.isEmpty()&&uiState.type==IdType.USER) {
                        Surface(
                            modifier = modifier.fillMaxSize()
                        ){
                            EmptyResult(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }else{
                        LazyColumn(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            when(uiState.type){
                                IdType.EMPLOYEE -> {
                                    items(employeeChildren.itemCount){ index->
                                        val child = employeeChildren[index]
                                        child?.let { child->
                                            child.apply {
                                                ChildCard(
                                                    child = Child(
                                                        id = id,
                                                        name = fullName,
                                                        age = age,
                                                        fatherName = fatherFullName,
                                                        motherName = motherFullName
                                                    ),
                                                    onClick = {
                                                        onNavigateToChildProfile(id)
                                                    },
                                                    modifier = Modifier.fillMaxSize()
                                                )
                                            }
                                        }
                                    }
                                    if(loadState.append == LoadState.Loading){
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
                                IdType.USER -> {
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
                                                    onNavigateToChildProfile(childId?:0)
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

@DarkAndLightModePreview
@Composable
fun ChildrenScreenPreview(){
    Hospital_AutomationTheme {
        var uiState by remember { mutableStateOf(doingProcessState)}
        ChildrenScreen(
            updateFetchingDataState = {},
            uiState = uiState,
            onNavigateUp = {},
            modifier = Modifier,
            employeeChildrenFlow = employeeChildren,
            onNavigateToChildProfile = {}
        )
    }
}
private val employeeChildren = emptyFlow<PagingData<ChildData>>()
private val children = listOf(
    ChildFullData(
        numberOfGuardians = 10,
        childId = 1,
        firstName = "Ali",
        lastName = "Ahmad",
        fatherFirstName = "Shafeek",
        fatherLastName = "Ahmad",
        motherFirstName = "Sara",
        motherLastName = "Sara",
        dateOfBirth = "2002-11-11",
        birthCertificateImgUrl = "",
        gender = "Male",
        employeeId = 0
    ),
    ChildFullData(
        numberOfGuardians = 10,
        childId = 2,
        firstName = "Ali",
        lastName = "Ahmad",
        fatherFirstName = "Shafeek",
        fatherLastName = "Ahmad",
        motherFirstName = "Sara",
        motherLastName = "Sara",
        dateOfBirth = "2002-01-01",
        birthCertificateImgUrl = "",
        gender = "Male",
        employeeId = 0
    ),
    ChildFullData(
        numberOfGuardians = 10,
        childId = 3,
        firstName = "Sara",
        lastName = "Momo",
        fatherFirstName = "Shafeek",
        fatherLastName = "Ahmad",
        motherFirstName = "Sara",
        motherLastName = "Sara",
        dateOfBirth = "2002-01-01",
        birthCertificateImgUrl = "",
        gender = "Female",
        employeeId = 0
    )
)
val successState = ChildrenUIState(
    userChildren = children,
    fetchingDataState = FetchingDataState.Success,
    type = IdType.USER
)
val emptyState = ChildrenUIState(
    userChildren = emptyList(),
    fetchingDataState = FetchingDataState.Success,
    type = IdType.USER
)
val errorState = ChildrenUIState(
    userChildren = children,
    fetchingDataState = FetchingDataState.ERROR,
    type = IdType.USER
)
val doingProcessState = ChildrenUIState(
    userChildren = emptyList(),
    fetchingDataState = FetchingDataState.LOADING,
    type = IdType.USER
)
