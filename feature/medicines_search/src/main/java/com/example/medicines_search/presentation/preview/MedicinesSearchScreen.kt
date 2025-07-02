package com.example.medicines_search.presentation.preview

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.constants.icons.AppIcons
import com.example.model.enums.ScreenState
import com.example.model.medicine.MedicineData
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.bottomBars.custom.MedicinesSearchBottomBar
import com.example.ui_components.components.card.MedicineCard
import com.example.ui_components.components.card.custom.ErrorComponent
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn
import com.example.ui_components.components.topbars.custom.SearchTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicinesSearchScreen(
    uiState: MedicinesSearchUIState,
    onAction: (MedicinesSearchUIAction)-> Unit,
    navigationActions: MedicinesSearchNavigationActions,
    medicines: LazyPagingItems<MedicineData>?,
    modifier: Modifier = Modifier,
) {
    medicines?.loadState?.run {
        when(refresh){
            is LoadState.Error -> onAction(MedicinesSearchUIAction.UpdateScreenState(ScreenState.ERROR))
            LoadState.Loading -> onAction(MedicinesSearchUIAction.UpdateScreenState(ScreenState.LOADING))
            is LoadState.NotLoading -> onAction(MedicinesSearchUIAction.UpdateScreenState(ScreenState.SUCCESS))
        }
    }

    BottomSheetScaffold(
        modifier = modifier,
        sheetContent = {
            if (uiState.selectedMedicines.isEmpty()) {

            }
        },
        topBar = {
            SearchTopBar(
                onSearchIconClick = {
                    onAction(
                        MedicinesSearchUIAction.ToggleTopBarState
                    )
                },
                onNavigateUp = navigationActions::navigateUp,
                onClearIconClick = {
                    onAction(
                        MedicinesSearchUIAction.UpdateQuery("")
                    )
                },
                query = uiState.query,
                state = uiState.topBarState,
                onQueryChanged = {
                    onAction(
                        MedicinesSearchUIAction.UpdateQuery(it)
                    )
                },
                title = stringResource(R.string.medicines),
                placeholder = R.string.search_for_guardians,
                onBackToDefault = {
                    onAction(
                        MedicinesSearchUIAction.ToggleTopBarState
                    )
                },
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding->
        AnimatedContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium16),
            targetState = uiState.screenState
        ) { state->
            when(state){
                ScreenState.IDLE -> Unit
                ScreenState.LOADING -> FetchingDataItem(Modifier.fillMaxSize())
                ScreenState.ERROR -> {
                    PullToRefreshColumn(
                        onRefresh = {
                            onAction(MedicinesSearchUIAction.Refresh)
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
                ScreenState.SUCCESS -> {
                    medicines?.let { medicines->
                        val count = medicines.itemCount
                        if (count == 0){
                            PullToRefreshColumn(
                                refreshing = uiState.isRefreshing,
                                onRefresh = {
                                    onAction(
                                        MedicinesSearchUIAction.Refresh
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                val description = if (uiState.query.isBlank()) stringResource(R.string.no_medicines)
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
                        }else{
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(MaterialTheme.spacing.small12),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                items(count) {index->
                                    val medicine = medicines[index]
                                    medicine?.run {
                                        MedicineCard(
                                            imageUrl = medImageUrl,
                                            medicineName = name,
                                            drug = pharmaceuticalTiter,
                                            price = price,
                                            numberOfPharmacies = numberOfPharmacies,
                                            onClick = {
                                                TODO("not designed yet")
                                            },
                                            onPharmaciesClick = {
                                                navigationActions.navigateToPharmacies(medicineId)
                                            },
                                            onButtonClick = {
                                                onAction(
                                                    MedicinesSearchUIAction.AddMedicineToPrescription(this)
                                                )
                                            },
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