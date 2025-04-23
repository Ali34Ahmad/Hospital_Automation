package com.example.ui_components.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.theme.Hospital_AutomationTheme

@Composable
fun IndexedText(
    index: Int,
    title: String,
    body: String,
    modifier: Modifier = Modifier,
) {
    val text = AnnotatedString.Builder().apply {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append("$title : ")
        pop()
        append(body)
    }.toAnnotatedString()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = "$index. ",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
    }


}

@Preview(showBackground = true)
@Composable
fun IndexedTextPreview() {
    Hospital_AutomationTheme {
        IndexedText(
            index = 1,
            title = "Immunodeficiency conditions",
            body = """
                Individuals with conditions like HIV/AIDS or other immune system disorders may also have a weakened response to the vaccine.
            """.trimIndent(),
        )
    }
}






