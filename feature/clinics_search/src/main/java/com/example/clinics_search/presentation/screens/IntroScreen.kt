package com.example.clinics_search.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ui_components.components.card.ChooseDepartmentCard

@Composable
fun IntroScreen(
    onStartButtonClick: ()-> Unit,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        modifier=modifier,
        contentAlignment = contentAlignment
    ){
        ChooseDepartmentCard(
            onStartButtonClick = onStartButtonClick,
        )
    }
}