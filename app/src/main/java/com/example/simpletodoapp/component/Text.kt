package com.example.simpletodoapp.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.simpletodoapp.ui.theme.Typography

@Composable
fun BasicTextTitle(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BasicTextBodyLarge(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = Typography.headlineLarge,
        color = Color.Gray
    )
}
@Composable
fun BasicTextBodyMedium(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = Typography.headlineMedium,
        color = Color.Gray
    )
}
