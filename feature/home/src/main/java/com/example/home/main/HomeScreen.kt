package com.example.home.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.constants.icons.AppIcons
import com.example.ui_components.components.card.IllustrationCard
import com.example.model.DrawerButton
import com.example.model.enums.ScreenState
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.drawers.EmployeeDrawer
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    uiActions: HomeUiActions,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let {
            Toast.makeText(
                context,
                it.asString(context),
                Toast.LENGTH_SHORT
            ).show()
            uiActions.clearToastMessage()
        }
    }

    val drawerButtons = listOf(
        DrawerButton(
            text = R.string.profile,
            image = AppIcons.Outlined.accountCircle,
            onClick = {
                uiActions.navigateToEmployeeProfileScreen()
            }
        ),
        DrawerButton(
            text = R.string.requests,
            image = AppIcons.Outlined.send,
            onClick = {
                uiActions.navigateToRequestsScreen()
            }
        ),
        DrawerButton(
            text = R.string.add_children,
            image = AppIcons.Outlined.child,
            onClick = {
                uiActions.navigateToAddedChildrenScreen()
            }
        ),
    )


    EmployeeDrawer(
        state = drawerState,
        title = R.string.medicare,
        buttons = drawerButtons,
        selectedIndex = null,
        onItemSelected = {
            drawerButtons[it].onClick()
            scope.launch {
                drawerState.close()
            }
        },
        onChangeThemeClick = {
            uiActions.onChangeTheme()
        },
        isDarkTheme = uiState.isDarkTheme,
    ) {
        Scaffold(
            topBar = {
                HospitalAutomationTopBar(
                    title = stringResource(R.string.medicare),
                    navigationIcon = AppIcons.Outlined.openDrawer,
                    onNavigationIconClick = {
                        scope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            } else {
                                drawerState.close()
                            }
                        }
                    },
                    imageUrl = null,
                )
            },
        ) { contentPadding ->
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .padding(
                                start = MaterialTheme.spacing.medium16,
                                end = MaterialTheme.spacing.medium16,
                                top = MaterialTheme.spacing.large24,
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (uiState.screenState == ScreenState.LOADING) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(
                                    MaterialTheme.sizing.circularProgressIndicatorSize,
                                )
                            )
                        } else if (uiState.screenState == ScreenState.ERROR) {
                            PullToRefreshBox(
                                refreshing = uiState.isRefreshing,
                                onRefresh = { uiActions.onRefresh() }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(scrollState),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    IllustrationCard(
                                        title = stringResource(R.string.network_error),
                                        description = stringResource(R.string.could_not_check_your_permissions),
                                        illustration = {
                                            Image(
                                                painter = painterResource(R.drawable.ill_error),
                                                contentDescription = null,
                                                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
                                            )
                                        }
                                    )
                                }
                            }
                        } else if (uiState.screenState == ScreenState.SUCCESS) {
                            PullToRefreshBox(
                                refreshing = uiState.isRefreshing,
                                onRefresh = { uiActions.onRefresh() }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(scrollState),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    HomeScreenSuccess(
                                        showPermissionCard = uiState.showPermissionCard,
                                        permissionGranted = uiState.isPermissionGranted,
                                        onStartButtonClick = {
                                            uiActions.onStartButtonClick()
                                        },
                                        navigateToAddChildScreen = {
                                            uiActions.navigateToAddChildScreen()
                                        },
                                        navigateToAddGuardianScreen = {
                                            uiActions.navigateToAddGuardianScreen()
                                        }
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

@DarkAndLightModePreview
@Composable
fun HomeScreenLoadingPreview() {
    Hospital_AutomationTheme {
        Surface {
            HomeScreen(
                uiState = HomeUiState(),
                uiActions = HomeUiActions(
                    navigationActions = mockHomeNavigationUiActions(),
                    businessActions = mockHomeBusinessUiActions(),
                )
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun HomeScreenPermissionRequiredPreview() {
    Hospital_AutomationTheme {
        Surface {
            HomeScreen(
                uiState = HomeUiState(
                    isPermissionGranted = false,
                    screenState = ScreenState.SUCCESS,
                ),
                uiActions = HomeUiActions(
                    navigationActions = mockHomeNavigationUiActions(),
                    businessActions = mockHomeBusinessUiActions(),
                )
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun HomeScreenPermissionGrantedPreview() {
    Hospital_AutomationTheme {
        Surface {
            HomeScreen(
                uiState = HomeUiState(
                    isPermissionGranted = true,
                    screenState = ScreenState.ERROR,
                ),
                uiActions = HomeUiActions(
                    navigationActions = mockHomeNavigationUiActions(),
                    businessActions = mockHomeBusinessUiActions(),
                )
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun HomeScreenHidePermissionCardPreview() {
    Hospital_AutomationTheme {
        Surface {
            HomeScreen(
                uiState = HomeUiState(
                    showPermissionCard = false,
                    screenState = ScreenState.SUCCESS,
                ),
                uiActions = HomeUiActions(
                    navigationActions = mockHomeNavigationUiActions(),
                    businessActions = mockHomeBusinessUiActions(),
                )
            )
        }
    }
}


@DarkAndLightModePreview
@Composable
fun HomeScreenErrorReadingPermissionPreview() {
    Hospital_AutomationTheme {
        Surface {
            HomeScreen(
                uiState = HomeUiState(
                    showPermissionCard = false,
                ),
                uiActions = HomeUiActions(
                    navigationActions = mockHomeNavigationUiActions(),
                    businessActions = mockHomeBusinessUiActions(),
                )
            )
        }
    }
}


