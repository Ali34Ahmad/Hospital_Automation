package com.example.clinics_search.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.clinics_search.presentation.screens.IntroScreen
import com.example.clinics_search.presentation.screens.SelectionScreen
import com.example.constants.icons.AppIcons
import com.example.model.DrawerButton
import com.example.model.doctor.clinic.ClinicFullData
import com.example.model.enums.TopBarState
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.drawers.EmployeeDrawer
import com.example.ui_components.components.topbars.custom.SearchTopBar

@Composable
fun ClinicsSearchScreen(
    viewModel: ClinicsSearchViewModel,
    navigationActions: ClinicsSearchNavigationActions,
    modifier: Modifier = Modifier
) {
    val clinics = viewModel.clinicsFlow.collectAsLazyPagingItems()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ClinicsSearchScreen(
        uiState = uiState.value,
        clinics = clinics,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        modifier = modifier
    )
}

@Composable
internal fun ClinicsSearchScreen(
    uiState: ClinicsSearchUIState,
    clinics: LazyPagingItems<ClinicFullData>?,
    onAction: (ClinicsSearchUIAction) -> Unit,
    navigationActions: ClinicsSearchNavigationActions,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(uiState.isDrawerOpened) {
        if(uiState.isDrawerOpened)drawerState.open()
        else drawerState.close()
    }
    val drawerButtons = listOf(
        DrawerButton(
            text = R.string.profile,
            image = AppIcons.Outlined.accountCircle,
            onClick = {
                navigationActions.navigateToDoctorProfile()
            }
        ),
        DrawerButton(
            text = R.string.notifications,
            image = AppIcons.Outlined.notification,
            onClick = {
                navigationActions.navigateToNotifications()
            }
        ),
        DrawerButton(
            text = R.string.medical_records,
            image = AppIcons.Outlined.medicalRecords,
            onClick = {
                navigationActions.navigateToMedicalRecords()
            },
            enabled = false
        ),
        DrawerButton(
            text = R.string.prescriptions,
            image = AppIcons.Outlined.prescription,
            onClick = {
                navigationActions.navigateToPrescriptions()
            },
            enabled = false
        ),
        DrawerButton(
            text = R.string.vaccines,
            image = AppIcons.Outlined.vaccines,
            onClick = {
                navigationActions.navigateToVaccines()
            },
            enabled = false
        ),
        DrawerButton(
            text = R.string.vaccine_table,
            image = AppIcons.Outlined.medicalRecords,
            onClick = {
                navigationActions.navigateToMedicalRecords()
            },
            enabled = false
        ),
        )
    EmployeeDrawer(
        state = drawerState,
        title = R.string.mail,
        buttons = drawerButtons,
        selectedIndex = null,
        onItemSelected = {
            drawerButtons[it].onClick()
            onAction(ClinicsSearchUIAction.ToggleDrawer)
        },
        onChangeThemeClick = {
            onAction(
                ClinicsSearchUIAction.ToggleTheme
            )
        },
        isDarkTheme = uiState.isDarkTheme
    ){
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface,
            topBar = {
                if (uiState.screenStep == ScreenStep.SELECTION) {
                    SearchTopBar(
                        placeholder = R.string.search_for_clinics,
                        title = stringResource(R.string.available_departments),
                        onSearchIconClick = {
                            onAction(
                                ClinicsSearchUIAction.UpdateTopBarMode(TopBarState.SEARCH)
                            )
                        },
                        onMenuOpen = {
                            onAction(
                                ClinicsSearchUIAction.ToggleDrawer
                            )
                        },
                        onClearIconClick = {
                            onAction(
                                ClinicsSearchUIAction.UpdateQuery("")
                            )
                        },
                        query = uiState.searchQuery,
                        state = uiState.topBarMode,
                        onQueryChanged = {
                            onAction(
                                ClinicsSearchUIAction.UpdateQuery(it)
                            )
                        },
                        onBackToDefault = {
                            onAction(
                                ClinicsSearchUIAction.UpdateTopBarMode(TopBarState.DEFAULT)
                            )
                        }
                    )
                }
            }
        ) { innerPadding ->
            AnimatedContent(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(MaterialTheme.spacing.medium16),
                targetState = uiState.screenStep
            ) { state ->
                when (state) {
                    ScreenStep.INTRO -> {
                        IntroScreen(
                            modifier = Modifier.fillMaxSize(),
                            onStartButtonClick = {
                                onAction(
                                    ClinicsSearchUIAction.GoToSelectionStep
                                )
                            }
                        )
                    }

                    ScreenStep.SELECTION -> {
                        clinics?.let {
                            SelectionScreen(
                                uiState = uiState,
                                onAction = onAction,
                                navigationActions = navigationActions,
                                clinics = it,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}