package com.example.ui_components.components.text_field

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_components.components.texts.OtpValidatorText
import com.example.ui_components.theme.Hospital_AutomationTheme
import com.example.ui_components.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpInputRow(
    otp: List<Int?>,
    onOtpChange: (Int, Int?) -> Unit,
    isValid: Boolean?,
    onValidate: (List<Int?>) -> Unit,
    @StringRes validText: Int,
    @StringRes invalidText: Int,
    modifier: Modifier = Modifier
) {
    val size = otp.size
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val scoop = rememberCoroutineScope()
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val focusRequesters = remember { List(size) { FocusRequester() } }
            for (i in 0 until size){
                OtpInputField(
                    modifier = Modifier.width(48.dp),
                    number = otp[i],
                    onNumberChange = {
                        onOtpChange(i, it)
                    },
                    onNumberEntered = {
                        onValidate(otp)
                        scoop.launch{
                            delay(100)
                            if (i < size - 1) {
                                focusRequesters[i + 1].requestFocus()
                            }
                        }
                    },
                    focusRequester = focusRequesters[i],
                    onBackButtonPress = {
                        if(i != 0){
                            focusRequesters[i - 1].requestFocus()
                        }
                    },
                    enabled = isValid != true
                )
            }

        }
        isValid?.let {
            Spacer(Modifier.height(12.dp))
            AnimatedContent(targetState = isValid, label = "") {
                when(it){
                    true -> {
                        OtpValidatorText(
                            isValid = true,
                            text = validText
                        )
                    }
                    false -> {
                        OtpValidatorText(
                            isValid = false,
                            text = invalidText
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OtpInputRowPreview() {
    Hospital_AutomationTheme {
        var otp = remember {
            mutableStateListOf<Int?>(null,null,null)
        }
        var isValid by remember { mutableStateOf<Boolean?>(null) }
            Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(otp.joinToString())
            Spacer(Modifier.height(16.dp))
            OtpInputRow(
                otp = otp,
                onOtpChange = { index, value ->
                    otp[index] = value
                },
                modifier = Modifier.fillMaxWidth(),
                isValid = isValid,
                validText = R.string.supporting_text,
                invalidText = R.string.supporting_text,
                onValidate = {
                    isValid = if(it.any{it == null})  null else otp.all { it == 0 }
                }
            )
        }
    }
}