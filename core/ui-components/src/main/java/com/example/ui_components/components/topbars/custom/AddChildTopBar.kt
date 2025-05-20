package com.example.ui_components.components.topbars.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui_components.components.topbars.HospitalAutomationTopBar
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun AddChildTopBar(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    HospitalAutomationTopBar(
        title = stringResource(R.string.add_child),
        onNavigationIconClick = onNavigateBack ,
        modifier = modifier,
        navigationIcon = HospitalAutomationIcons.arrowBack,
    )
}