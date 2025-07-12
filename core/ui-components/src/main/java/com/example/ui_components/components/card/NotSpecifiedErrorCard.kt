package com.example.ui_components.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun NotSpecifiedErrorCard(modifier: Modifier = Modifier) {
    IllustrationCard(
        modifier = modifier,
        illustration = {
            Image(
                painter = painterResource(R.drawable.ill_error),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
            )
        },
        title = stringResource(R.string.network_error),
        description = stringResource(R.string.something_went_wrong),
    )
}

@DarkAndLightModePreview
@Composable
fun NotSpecifiedErrorCardPreview(){
    Hospital_AutomationTheme {
        Surface {
            NotSpecifiedErrorCard(
                Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}