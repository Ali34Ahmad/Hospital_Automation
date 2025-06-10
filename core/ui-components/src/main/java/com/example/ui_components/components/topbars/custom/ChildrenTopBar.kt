package com.example.ui_components.components.topbars.custom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui_components.R
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.constants.icons.AppIcons

@Composable
fun ChildrenTopBar(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier) {
    HospitalAutomationTopBar(
        title = stringResource(R.string.children),
        onNavigationIconClick = onNavigateUp,
        modifier = modifier,
        navigationIcon = AppIcons.Outlined.arrowBack
    )
}