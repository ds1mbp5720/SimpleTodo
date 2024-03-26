package com.example.simpletodoapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.simpletodoapp.ui.theme.Highlight

@Composable
fun RoundImage(
    modifier: Modifier,
    imageId: Int
) {
    Image(
        modifier = modifier
            .size(180.dp)
            .clip(CircleShape),
        painter = painterResource(imageId),
        contentScale = ContentScale.FillBounds,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
        contentDescription = "icon_button"
    )
}