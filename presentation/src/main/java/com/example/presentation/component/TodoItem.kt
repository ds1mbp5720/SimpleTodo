package com.example.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    task: String,
    date: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray
            )
            .padding(
                horizontal = 10.dp,
                vertical = 15.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextBodyMedium(
            modifier = Modifier
                .weight(2f),
            text = task,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextBodySmall(
            modifier = Modifier
                .weight(.8f),
            text = date
        )
    }
}

@Preview
@Composable
fun testTodoItem() {
    TodoItem(modifier = Modifier, task = "test task", date = "2022/ 04/ 01 / 18:00 pm")
}