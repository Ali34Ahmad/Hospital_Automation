package com.example.ui_components.components.text_field

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.ui.theme.Hospital_AutomationTheme
import com.example.ui.theme.sizing
import com.example.ui.theme.spacing
import com.example.ui_components.R
import com.example.ui_components.components.texts.OtpValidatorText

@Composable
fun OtpInputRow(
    otp: List<Int?>,
    onOtpChange: (index: Int, value: Int?) -> Unit,
    onValidate: (List<Int?>) -> Unit,
    modifier: Modifier = Modifier,
    invalidText: String?,
    enabled: Boolean=true,
    textFieldSize: Dp = MaterialTheme.sizing.medium56,
    isValid: Boolean? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {

    val size = otp.size
    val focusRequesters by remember {
        mutableStateOf(
        List(size) { FocusRequester() }
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            repeat(size) { index->
                OtpInputField(
                    modifier = Modifier.width(textFieldSize),
                    number = otp[index],
                    onNumberChange = { newNumber->
                        onOtpChange(index, newNumber)
                    },
                    enabled = enabled,
                    onNumberEntered = {
                        //if the user has entered the full otp number, we will validate it.
                        if(otp.all { it != null }){
                            onValidate(otp)
                        }else{
                            if (index < size-1) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    focusRequester = focusRequesters[index],
                    onMoveBack = {
                        if (index > 0) {
                            focusRequesters[index - 1].requestFocus()
                        }
                    },
                    isError = isValid == true
                )
            }
        }
        AnimatedVisibility(isValid == true) {
            Spacer(Modifier.height(MaterialTheme.spacing.small12))
            OtpValidatorText(
                isValid = true,
                text = invalidText.toString()
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OtpInputRowPreview() {
    Hospital_AutomationTheme {
        val otp = remember {
            mutableStateListOf<Int?>(null, null, null)
        }
        Surface {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacing.medium16)
            ) {
                Text(otp.joinToString())
                Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                OtpInputRow(
                    otp = otp,
                    onOtpChange = { index, value ->
                        otp[index] = value
                    },
                    invalidText = stringResource(R.string.supporting_text),
                    onValidate = {

                    }
                )
            }
        }
    }
}