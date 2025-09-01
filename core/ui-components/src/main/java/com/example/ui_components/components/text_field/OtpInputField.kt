package com.example.ui_components.components.text_field

import android.view.KeyEvent
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.ui.helper.DarkAndLightModePreview
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing

@Composable
fun OtpInputField(
    number: Int?,
    onNumberChange: (Int?) -> Unit,
    onNumberEntered: (Int) -> Unit,
    onMoveBack: () -> Unit,
    focusRequester: FocusRequester,
    enabled: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier,
    style : TextStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
    shape : Shape = RoundedCornerShape(MaterialTheme.sizing.small8),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor =  MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
        focusedTextColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        errorBorderColor = MaterialTheme.colorScheme.error,
    ),
    aspectRatio: Float = 0.8f,
    width: Dp = MaterialTheme.sizing.medium48
) {
    val value = number?.toString().orEmpty()
    val textFieldValue = TextFieldValue(
        text = value,
        selection = TextRange(value.length),
    )
    OutlinedTextField(
        modifier = modifier
            .onKeyEvent { event ->
                //if the user press on the del button from the keyboard
                if(event.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL){
                    if(textFieldValue.text.isEmpty()){
                        //if no value in the text field we will move back
                        onMoveBack()
                    }else{
                        //delete the number
                        onNumberChange(null)
                    }
                    return@onKeyEvent true
                }
                false
            }
            .aspectRatio(aspectRatio)
            .width(width)
            .focusRequester(focusRequester)
//            .onFocusChanged {
//                if(it.isFocused){
//                    onNumberChange(null)
//                }
//            }
            ,
        value = textFieldValue,
        onValueChange = {newValue->
            val text = newValue.text.takeLast(1).filter { it.isDigit() }
            val number = text.toIntOrNull()

            onNumberChange(number)

            number?.let { number->
            onNumberEntered(number)
            }
        },
        textStyle = style,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
        singleLine = true,
        shape = shape,
        colors = colors,
        isError = isError,
        enabled = enabled,
    )
}


@DarkAndLightModePreview
@Composable
fun OtpInputFieldPreview() {
    var number by remember { mutableStateOf<Int?>(null) }
    OtpInputField(
        modifier = Modifier.width(MaterialTheme.sizing.medium56),
        number = number,
        onNumberChange = {
            number = it
        },
        onNumberEntered = {

        },
        focusRequester = FocusRequester(),
        onMoveBack = {},
        enabled = true,
        isError=false
    )

}















