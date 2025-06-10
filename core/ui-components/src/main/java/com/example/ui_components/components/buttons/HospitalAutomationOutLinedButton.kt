package com.example.ui_components.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.additionalShapes
import com.example.ui_components.R

@Composable
fun HospitalAutomationOutLinedButton(
    onClick: () -> Unit,
    text: String,
    @DrawableRes icon: Int? = null,
    enabled: Boolean=true,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
        ),
        border = BorderStroke(
            color = MaterialTheme.colorScheme.primary,
            width = 1.dp
        ),
        shape = MaterialTheme.additionalShapes.small12,
        modifier = modifier,
        enabled = enabled,
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
            Text(
                text,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationOutlinedButtonPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationOutLinedButton(
            onClick = {},
            text = stringResource(R.string.login),
            icon = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationOutlinedButtonWithIconPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationOutLinedButton(
            onClick = {},
            text = stringResource(R.string.upload_file),
            icon = R.drawable.call
        )
    }
}