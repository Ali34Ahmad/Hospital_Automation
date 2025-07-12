package com.example.ui_components.components.text_field

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.constants.icons.AppIcons
import com.example.model.enums.AgeUnit
import com.example.model.menu.DropDownMenuItem
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui_components.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDropDownMenu(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onDropDownItemSelected: (index: Int) -> Unit,
    dropDownItems: List<DropDownMenuItem>,
    selectedItemIndex: Int?,
    modifier: Modifier = Modifier,
    textFieldShape: Shape= TextFieldDefaults.shape,
    enabled:Boolean=true,
    isError:Boolean=true,
) {
    val text = if (selectedItemIndex == null) {
        stringResource(R.string.none)
    } else {
        dropDownItems[selectedItemIndex].name
    }

    val icon = if (expanded) {
        AppIcons.Outlined.dropUp
    } else {
        AppIcons.Outlined.dropDown
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier
//            .width(IntrinsicSize.Max)
        ,
    ) {
        HospitalAutomationTextFiled(
            value = text,
            onValueChange = {},
            readOnly = true,
            label = R.string.to_age,
            trailingIcon = icon,
            shape=textFieldShape,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                errorContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .menuAnchor(),
            enabled = enabled,
            isError = isError,
            )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
        ) {
            dropDownItems.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onDropDownItemSelected(index)
                        onDismissRequest()
                    },
                    text = { Text(selectionOption.name) },
                )
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun TextFieldWithDropDownMenuPreview() {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndexItem: Int? = null

    Hospital_AutomationTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                HospitalAutomationTextFiled(
                    value = "12",
                    onValueChange = {},
                    maxLines = 1,
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    label=R.string.from_age,
                )
                TextFieldWithDropDownMenu(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    onDismissRequest = { expanded = false },
                    onDropDownItemSelected = { selectedIndexItem = it },
                    dropDownItems = AgeUnit.entries.map { DropDownMenuItem(it.name) },
                    selectedItemIndex = selectedIndexItem,
                    textFieldShape = RoundedCornerShape(
                        topStart = 0.dp,
                        bottomStart = 0.dp,
                        topEnd = 4.dp,
                        bottomEnd = 4.dp
                    ),
                    modifier = Modifier
                )
            }
        }
    }
}