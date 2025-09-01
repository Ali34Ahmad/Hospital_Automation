package com.example.pharmacy_medicines.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.constants.icons.AppIcons
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineSummaryData
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.card.MedicineCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.progress_indicator.SmallCircularProgressIndicator
import com.example.ui_components.components.pull_to_refresh.PullToRefreshBox
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.SearchTopBar

@Composable
fun PharmacyMedicineScreen(
    viewModel: PharmacyMedicinesViewModel,
    navigationActions: PharmacyMedicinesNavigationActions,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val medicines = viewModel.medicinesFlow.collectAsLazyPagingItems()

    PharmacyMedicineScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        navigationActions = navigationActions,
        medicines = medicines,
        modifier = modifier
    )
}

@Composable
fun PharmacyMedicineScreen(
    uiState: PharmacyMedicinesUIState,
    medicines: LazyPagingItems<MedicineSummaryData>?,
    onAction: (PharmacyMedicinesUIAction) -> Unit,
    navigationActions: PharmacyMedicinesNavigationActions,
    modifier: Modifier = Modifier
) {
    medicines?.loadState?.run {
        when(refresh){
            is LoadState.Error ->
                onAction(PharmacyMedicinesUIAction.UpdateScreenState(ScreenState.ERROR))

            LoadState.Loading ->
                onAction(
                    PharmacyMedicinesUIAction.UpdateScreenState(
                        ScreenState.LOADING
                    )
                )

            is LoadState.NotLoading ->
                onAction(
                    PharmacyMedicinesUIAction.UpdateScreenState(
                        ScreenState.SUCCESS
                    )
                )

        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            SearchTopBar(
                imageUrl = uiState.imageUrl,
                onSearchIconClick = {
                    onAction(PharmacyMedicinesUIAction.ToggleTopBarState)
                },
                onNavigationIconClick = navigationActions::navigateUp,
                onBackToDefault = {
                    onAction(PharmacyMedicinesUIAction.ToggleTopBarState)
                },
                onClear = {
                    onAction(PharmacyMedicinesUIAction.UpdateQuery(""))
                },
                query = uiState.query,
                state = uiState.topBarState,
                onQueryChanged = {newValue->
                    onAction(PharmacyMedicinesUIAction.UpdateQuery(newValue))
                },
                title = uiState.title,
                placeholder = R.string.search_for_medicines,
                onTitleClick = {
                    navigationActions.navigateToPharmacy(
                        uiState.pharmacyId
                    )
                },
                defaultLeadingIcon = AppIcons.Outlined.arrowBack,

            )
        }
    ) {innerPadding->
        AnimatedContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16),
            targetState = uiState.screenState
        ) {state->
            when(state){
                ScreenState.IDLE -> Unit
                ScreenState.LOADING -> FetchingDataItem(Modifier.fillMaxSize())
                ScreenState.ERROR -> {
                    PullToRefreshColumn(
                        onRefresh = {
                            onAction(PharmacyMedicinesUIAction.Refresh)
                        },
                        refreshing = uiState.isRefreshing,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        ErrorComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                ScreenState.SUCCESS ->{
                    medicines?.let { medicines->
                        val count = medicines.itemCount
                        if (count == 0) {
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        PharmacyMedicinesUIAction.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                val description =
                                    if (uiState.query.isBlank()) stringResource(R.string.no_medicines)
                                    else stringResource(
                                        R.string.no_results_subtitle,
                                        R.string.medicines,
                                        uiState.query.trim()
                                    )
                                ErrorComponent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.spacing.medium16),
                                    title = stringResource(R.string.no_matching_result),
                                    description = description
                                )
                            }
                        } else {
                            PullToRefreshBox(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        PharmacyMedicinesUIAction.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize()
                            ){
                                val maxLineSpan = 2
                                LazyVerticalGrid(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    columns = GridCells.Fixed(maxLineSpan),
                                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small12),
                                    verticalArrangement =  Arrangement.spacedBy(MaterialTheme.spacing.small12),
                                ){
                                    items(
                                        count,
                                        key = {index->
                                            val medicine = medicines[index]
                                            medicine?.id?:0
                                        }
                                    ){index->
                                        val medicine = medicines[index]

                                        medicine?.let { medicine->
                                            MedicineCard(
                                                imageUrl = medicine.imageUrl.orEmpty(),
                                                medicineName = medicine.name,
                                                drug = medicine.pharmaceuticalTiter,
                                                price = medicine.price?:0,
                                                numberOfPharmacies = null,
                                                onClick = {
                                                    navigationActions.navigateToMedicineDetails(medicine.id)
                                                },
                                                onPharmaciesClick = {},
                                                onButtonClick = {},
                                                hasActions = false,
                                            )
                                        }
                                    }

                                    if(medicines.loadState.append == LoadState.Loading){
                                        item(span = { GridItemSpan(maxLineSpan) }) {
                                            SmallCircularProgressIndicator(
                                                modifier = Modifier.fillMaxWidth()
                                                    .padding(MaterialTheme.spacing.medium16)
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