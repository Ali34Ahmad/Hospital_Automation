package com.example.ui_components.components.card.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ui.theme.sizing
import com.example.ui_components.R
import com.example.ui_components.components.card.IllustrationCard

@Composable
fun PermissionRequiredCard(modifier: Modifier = Modifier) {
    Surface{
        IllustrationCard(
            modifier=modifier,
            illustration = {
                Image(painter = painterResource(R.drawable.ill_permission),
                    contentDescription = null,
                    modifier = Modifier.size(MaterialTheme.sizing.illustrationImageSize))
            },
            title = stringResource(R.string.permission_required),
            description = stringResource(R.string.permission_required_description),
        )
    }
}