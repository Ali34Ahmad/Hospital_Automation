package com.example.ui_components.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.icons.HospitalAutomationIcons
import com.example.ui_components.theme.Hospital_AutomationTheme

@Composable
fun Option(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    isSelected : Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = isSelected, label = "isSelectedTransition")

    val color by transition.animateColor(label = "color") { isSelected ->
        if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background
    }

    val borderColor by transition.animateColor(label = "borderColor") { isSelected ->
        if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    }

    val borderWidth by transition.animateDp(label = "borderWidth") { isSelected ->
        if (isSelected) 1.dp else 0.dp
    }

    Surface(
        modifier = modifier
            .height(46.dp)
            .fillMaxWidth(1f)
            .border(width = borderWidth, borderColor, shape = MaterialTheme.shapes.small)
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() },
        color = color,
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = painterResource(icon),null)
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OptionPreview() {
    Hospital_AutomationTheme {
        var isSelected by remember {
            mutableStateOf(false)
        }
        Option(
            icon = HospitalAutomationIcons.man,
            text = R.string.male,
            isSelected = isSelected,
            modifier = Modifier.width(182.dp),
            onClick = {
                isSelected = !isSelected
            }

        )
    }
}








