package com.example.ui_components.components.card

import androidx.annotation.DrawableRes
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
fun IllustrationCardWithNoActions(
    title:String,
    description:String,
    @DrawableRes imageRes:Int,
    modifier: Modifier = Modifier,
) {
    IllustrationCard(
        title = title,
        description = description,
        modifier = modifier,
        illustration = {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize)
            )
        }
    )
}

@DarkAndLightModePreview
@Composable
fun IllustrationCardWithNoActionsPreview() {
    Hospital_AutomationTheme {
        Surface {
            IllustrationCardWithNoActions(
                title = stringResource(R.string.not_found),
                description = stringResource(R.string.medicine_not_found_description),
                imageRes = R.drawable.ill_error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}