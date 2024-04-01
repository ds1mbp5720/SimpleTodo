package com.example.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.component.BasicButton
import com.example.presentation.component.BasicTextBodyLarge
import com.example.presentation.component.BasicTextBodyMedium
import com.example.presentation.component.RoundImage

@Composable
fun CheckProfileScreen(
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 100.dp,
                bottom = 50.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        RoundImage(
            modifier = Modifier,
            imageId = R.drawable.profile,
            size = 180
        )
        Column {
            BasicTextBodyLarge(
                modifier = Modifier,
                text = "Test Name"
            )
            Spacer(modifier = Modifier.height(30.dp))
            BasicTextBodyMedium(
                modifier = Modifier,
                text = "YYYY. MM. DD"
            )
        }
        BasicButton(
            modifier = Modifier
                .padding(horizontal = 40.dp),
            text = stringResource(id = R.string.str_btn_start)
        ) {
            onStartClick()
        }
    }
}