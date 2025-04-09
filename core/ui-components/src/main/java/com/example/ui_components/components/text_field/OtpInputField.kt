package com.example.ui_components.components.text_field

import android.view.KeyEvent
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpInputField(
    number: Int? = null,
    onNumberChange: (Int?) -> Unit,
    onNumberEntered: (Int) -> Unit,
    onBackButtonPress: () -> Unit,
    focusRequester: FocusRequester,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val value = number?.toString().orEmpty()
    OutlinedTextField(
        modifier = modifier
            .onKeyEvent { event ->
                if(event.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL){
                    onNumberChange(null)
                    onBackButtonPress()
                }
                false
            }
            .aspectRatio(1f)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if(it.isFocused){
                    onNumberChange(null)
                }
            },
        value = value,
        onValueChange = {
            val num = it.map { char ->
                if (char.isDigit()) char.toString().toInt()  else null
            }.lastOrNull()

            onNumberChange(num)
            num?.let {
                onNumberEntered(it)
            }
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
        singleLine = true,
        shape = RoundedCornerShape(8),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor =  MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        enabled = enabled
    )
}


@Preview()
@Composable
fun OtpInputFieldPreview() {
    var number by remember { mutableStateOf<Int?>(null) }
    OtpInputField(
        modifier = Modifier.width(48.dp),
        number = number,
        onNumberChange = {
            number = it
        },
        onNumberEntered = {

        },
        focusRequester = FocusRequester(),
        onBackButtonPress = {},
        enabled = true
    )

}















