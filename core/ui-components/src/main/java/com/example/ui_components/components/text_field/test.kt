package com.example.ui_components.components.text_field

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AutoFocusTextFields(numberOfFields: Int) {
    val textValues = remember { mutableStateListOf<String>() }
    val focusRequesters = remember { List(numberOfFields) { FocusRequester() } }

    // Initialize textValues list with empty strings
    if (textValues.isEmpty()) {
        repeat(numberOfFields) {
            textValues.add("")
        }
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in 0 until numberOfFields) {
            OutlinedTextField(
                value = textValues[i],
                onValueChange = { newValue ->
                    if (newValue.length <= 1) { // Limit to one character
                        textValues[i] = newValue
                        if (newValue.isNotEmpty() && i < numberOfFields - 1) {
                            focusRequesters[i + 1].requestFocus() // Move focus to next field
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .focusRequester(focusRequesters[i]),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // You can change the keyboard type
                label = { Text(text = "خانة ${i + 1}") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    AutoFocusTextFields(
        numberOfFields = 3
    )
}