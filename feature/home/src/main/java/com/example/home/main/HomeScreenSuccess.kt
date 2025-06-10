package com.example.home.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ui_components.components.card.IllustrationCard
import com.example.ui.theme.sizing
import com.example.ui_components.components.card.PermissionGrantedCard
import com.example.ui_components.R
import com.example.ui_components.components.card.EmployeeWorkTipsCard

@Composable
fun HomeScreenSuccess(
    showPermissionCard:Boolean,
    permissionGranted:Boolean,
    onStartButtonClick:()->Unit,
    navigateToAddChildScreen:()->Unit,
    navigateToAddGuardianScreen:()->Unit,
    modifier: Modifier = Modifier,
) {
    if (showPermissionCard) {
        if (permissionGranted) {
            PermissionGrantedCard(
                title = stringResource(R.string.permission_granted),
                description = stringResource(R.string.employee_permission_granted_description),
                onStartButtonClick = {
                    onStartButtonClick()
                }
            )
        } else {
            IllustrationCard(
                title = stringResource(R.string.permission_required),
                description = stringResource(R.string.employee_permission_required_description),
                modifier = modifier,
                illustration = {
                    Image(
                        painter = painterResource(R.drawable.ill_permission),
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
                    )
                },
            )
        }
    }else{
        EmployeeWorkTipsCard(
            title = stringResource(R.string.work_tips),
            description = stringResource(R.string.work_tips_description),
            onAddChildButtonClick = {
                navigateToAddChildScreen()
            },
            onAddGuardianButtonClick = {
                navigateToAddGuardianScreen()
            }
        )
    }
}