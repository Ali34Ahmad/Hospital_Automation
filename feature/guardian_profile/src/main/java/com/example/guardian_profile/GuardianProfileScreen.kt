package com.example.guardian_profile

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.constants.enums.Gender
import com.example.model.enums.FetchingDataState
import com.example.ui.theme.spacing
import com.example.ui_components.components.bottomBars.custom.GuardianProfileBottomBar
import com.example.ui_components.components.card.custom.CustomGuardianProfileCard
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.topbars.custom.GuardianProfileTopBar

@Composable
fun GuardianProfileScreen(
    viewModel: GuardianProfileViewModel,
    onAction: (GuardianProfileActions) -> Unit,
    modifier: Modifier = Modifier
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    GuardianProfileScreen(
        uiState = uiState.value,
        onNavigateBack = {
            onAction(GuardianProfileActions.NavigateBack)
        },
        onOpen = {phoneNumber->
            onAction(GuardianProfileActions.Open(phoneNumber))
        },
        onOpenEmail = {email->
            onAction(GuardianProfileActions.OpenEmail(email))
        },
        onSetAsGuardian = {
            onAction(GuardianProfileActions.SetAsGuardian)
        },
        onNavigateToChildren = {
            uiState.value.guardianData?.let {
                onAction(GuardianProfileActions.NavigateToChildren(it.userId))
            }
        },
        hasBottomBar = uiState.value.hasBottomBar,
        modifier = modifier
    )
}
@Composable
fun GuardianProfileScreen(
    uiState: GuardianProfileUIState,
    onNavigateBack: () -> Unit,
    onOpen: (String)-> Unit,
    onOpenEmail: (String)-> Unit,
    onNavigateToChildren: (Int)-> Unit,
    onSetAsGuardian: ()-> Unit,
    hasBottomBar: Boolean,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            AnimatedVisibility(uiState.fetchGuardianState!= FetchingDataState.Success){
                GuardianProfileTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    onNavigateUp = onNavigateBack,
                )
            }
        },
        bottomBar = {
           if (hasBottomBar){
                GuardianProfileBottomBar(
                    onButtonClick = onSetAsGuardian,
                    state = uiState.setAsGuardianState,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium16),
                    errorMessage = uiState.errorMessage?.asString()
                )
            }
        }
    ) { innerPadding ->

            AnimatedContent(
                modifier = modifier.padding(innerPadding)
                    .padding(
                        MaterialTheme.spacing.medium16
                    ),
                targetState = uiState.fetchGuardianState
            ) {
                when(it){
                    FetchingDataState.READY ->{
                        Text("ready to send")
                    }
                    FetchingDataState.DOING_PROCESS ->{
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            FetchingDataItem()
                        }
                    }
                    FetchingDataState.ERROR ->{
                        SomeThingWentWrong(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    FetchingDataState.Success -> {
                        uiState.guardianData?.let { data->
                            CustomGuardianProfileCard(
                                fullName = "${data.firstName} ${data.lastName}",
                                phoneNumber = data.phoneNumber,
                                imageUrl = data.imgUrl?:"",
                                address = "${data.addressCity}-${data.addressRegion}-${data.addressStreet}",
                                gender = data.gender?: Gender.FEMALE.name.lowercase(),
                                onNavigateUp = onNavigateBack,
                                onPhoneNumberButtonClick = {
                                    onOpen(data.phoneNumber)
                                },
                                onEmailButtonClick = {
                                    onOpenEmail(data.email)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                onChildrenButtonClick = {
                                    onNavigateToChildren(data.userId)
                                }
                            )
                        }
                    }
                }
            }
    }
}