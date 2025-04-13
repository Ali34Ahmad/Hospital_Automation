package com.example.ui_components.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.constants.icons.AppIcons
import com.example.ui_components.R
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes

@Composable
fun HospitalAutomationButton(
    onClick: () -> Unit,
    text: String,
    @DrawableRes icon: Int?=null,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.additionalShapes.small12,
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(painter = painterResource(icon), null)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationButtonPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.signup)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationButtonWithIconPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationButton(
            onClick = {},
            icon = AppIcons.Outlined.call,
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.signup)
        )
    }
}

