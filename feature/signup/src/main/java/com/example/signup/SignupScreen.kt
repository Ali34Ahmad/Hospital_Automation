package com.example.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.network.NetworkConstants

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {

}
@Preview(showBackground = true)
@Composable
fun Preview() {
    Box(
        modifier = Modifier.size(40.dp)
            .padding(12.dp)
            .background(Color.Blue)
    ){
    }
}