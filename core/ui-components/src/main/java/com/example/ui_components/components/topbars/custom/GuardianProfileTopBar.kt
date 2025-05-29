package com.example.ui_components.components.topbars.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.icons.HospitalAutomationIcons
import com.example.ui_components.R
@Composable
fun GuardianProfileTopBar(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    HospitalAutomationTopBar(
        navigationIcon = HospitalAutomationIcons.arrowBack,
        title = stringResource(R.string.guardian_profile),
        onNavigationIconClick = onNavigateUp,
        modifier = modifier,
    )
}