package com.example.guardians.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.enums.ScreenState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.items.custom.CenteredMessage
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.items.custom.SomeThingWentWrong
import com.example.ui_components.components.list_items.GuardianListItem
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.icons.HospitalAutomationIcons
import androidx.compose.runtime.getValue
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
                navigationIcon = HospitalAutomationIcons.arrowBack,
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
                        SomeThingWentWrong(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    ScreenState.SUCCESS ->{
                        if(uiState.data.isEmpty()){
                            CenteredMessage(
                                modifier = Modifier.fillMaxSize()
                            )
                        }else{
                            LazyColumn(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small8),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                items(uiState.data) { guardian->
                                    GuardianListItem(
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