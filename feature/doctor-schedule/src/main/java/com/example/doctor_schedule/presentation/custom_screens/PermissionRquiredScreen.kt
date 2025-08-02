package com.example.doctor_schedule.presentation.custom_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.model.enums.ScreenState
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.card.IllustrationCard
import com.example.ui_components.components.card.custom.PermissionRequiredCard
import com.example.ui_components.components.items.custom.FetchingDataItem
import com.example.ui_components.components.pull_to_refresh.PullToRefreshColumn

@Composable
fun PermissionRequiredScreen(
    state: ScreenState,
    isRefreshing: Boolean,
    onRefresh: ()-> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier){
        when(state){
            ScreenState.LOADING -> FetchingDataItem(Modifier.fillMaxSize())
            ScreenState.ERROR -> {
                PullToRefreshColumn(
                    refreshing = isRefreshing,
                    modifier = Modifier.fillMaxSize(),
                    onRefresh = onRefresh,
                    verticalArrangement = Arrangement.Center
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
            //if it's idle or success its the same.
            ScreenState.IDLE -> {
                PullToRefreshColumn(
                    refreshing = isRefreshing,
                    modifier = Modifier.fillMaxSize(),
                    onRefresh = onRefresh,
                    verticalArrangement = Arrangement.Center
                ){
                    PermissionRequiredCard(
                        Modifier.fillMaxWidth()
                    )
                }

            }
            ScreenState.SUCCESS -> Unit
        }
    }
}
