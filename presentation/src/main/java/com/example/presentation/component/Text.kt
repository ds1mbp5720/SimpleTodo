package com.example.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.Typography

@Composable
fun BasicTextTitle(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier
            .padding(start = 10.dp),
        text = text,
        textAlign = TextAlign.Start,
        style = Typography.displayMedium,
        color = Color.Gray
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

@Composable
fun BasicTextBodySmall(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = Typography.labelSmall,
        color = Color.Gray
    )
}
