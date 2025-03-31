package com.example.ui_components.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons

@Composable
fun HospitalAutomationOutLinedButton(
    onClick: () -> Unit,
    @StringRes  text: Int,
    @DrawableRes icon: Int?,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(40.dp),
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(icon != null){
                Icon(painter = painterResource(icon),null)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(stringResource(text))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalAutomationOutlinedButtonPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationOutLinedButton(
            onClick = {},
            text = R.string.login,
            null
        )
    }
}
@Preview(showBackground = true)
@Composable
fun HospitalAutomationOutlinedButtonWithIconPreview() {
    Hospital_AutomationTheme {
        HospitalAutomationOutLinedButton(
            modifier = Modifier.width(182.dp),
            onClick = {},
            text = R.string.upload_file,
            icon = HospitalAutomationIcons.attachment
        )
    }
}