package com.example.ui_components.components.text_field

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.model.enums.AgeUnit
import com.example.model.helper.ext.capitalFirstChar
import com.example.model.menu.DropDownMenuItem
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.spacing
import com.example.ui_components.R

@Composable
fun InputWithDropdownSelector(
    editableTextValue: String,
    onEditableTextValueChange: (String) -> Unit,
    @StringRes editableTextLabel: Int?,
    isMenuExpanded: Boolean,
    onMenuExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onDropDownItemSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedIndexItem: Int? = null,
    enabled: Boolean = true,
    isRequired: Boolean = false,
    isInputError: Boolean = false,
    isDropDownMenuError: Boolean = false,
    supportingText: String? = null,
    dropDownMenuItems:List<DropDownMenuItem>,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
    ),
) {
    Row(
        modifier = modifier,
    ) {
        HospitalAutomationTextFiled(
            value = editableTextValue,
            onValueChange = onEditableTextValueChange,
            maxLines = 1,
            shape = RoundedCornerShape(
                topStart = 4.dp,
                bottomStart = 4.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            ),
            label = editableTextLabel,
            enabled = enabled,
            isRequired = isRequired,
            isError = isInputError,
            supportingText = supportingText,
            modifier = Modifier.weight(0.4f),
            keyboardOptions = keyboardOptions,
        )
        Spacer(Modifier.width(MaterialTheme.spacing.extraSmall1))
        TextFieldWithDropDownMenu(
            expanded = isMenuExpanded,
            onExpandedChange = onMenuExpandedChange,
            onDismissRequest = onDismissRequest,
            onDropDownItemSelected = onDropDownItemSelected,
            dropDownItems = dropDownMenuItems,
            selectedItemIndex = selectedIndexItem,
            textFieldShape = RoundedCornerShape(
                topStart = 0.dp,
                bottomStart = 0.dp,
                topEnd = 4.dp,
                bottomEnd = 4.dp
            ),
            modifier = Modifier.weight(0.25f),
            enabled = enabled,
            isError = isDropDownMenuError,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun InputWithDropdownSelectorPreview() {
    Hospital_AutomationTheme {
        Surface {
            InputWithDropdownSelector(
                editableTextValue = "12",
                onEditableTextValueChange = {},
                isMenuExpanded = true,
                onMenuExpandedChange = {},
                onDismissRequest = { },
                onDropDownItemSelected = {},
                modifier = Modifier,
                selectedIndexItem = null,
                editableTextLabel = R.string.from_age,
                dropDownMenuItems = AgeUnit.entries.map { DropDownMenuItem(it.name.capitalFirstChar()) }
            )
        }
    }
}