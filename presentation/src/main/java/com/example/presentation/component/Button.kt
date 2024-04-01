package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.Highlight
import com.example.presentation.theme.Typography

@Composable
fun BasicButton(
    modifier: Modifier,
    text: String,
    buttonColor: Color = Highlight,
    textColor: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center,
    clickEvent: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 5.dp
            ),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        onClick = clickEvent
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = textAlign,
            style = Typography.labelLarge,
            color = textColor,
        )
    }
}

@Composable
fun BasicRowButton(
    modifier: Modifier,
    textStart: String,
    textCenter: String,
    textEnd: String,
    buttonColor: Color = Highlight,
    textColor: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center,
    clickEvent: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 5.dp
            ),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        onClick = clickEvent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                modifier = Modifier,
                text = textStart,
                textAlign = textAlign,
                style = Typography.labelLarge,
                color = textColor,
            )
            Text(
                modifier = Modifier,
                text = textCenter,
                textAlign = textAlign,
                style = Typography.labelLarge,
                color = textColor,
            )
            Text(
                modifier = Modifier,
                text = textEnd,
                textAlign = textAlign,
                style = Typography.labelLarge,
                color = textColor,
            )
        }
    }
}

@Composable
fun RoundIconButton(
    modifier: Modifier,
    imageId: Int,
    clickEvent: () -> Unit
) {
    IconButton(
        modifier = modifier
            .size(180.dp)
            .background(
                color = Highlight,
                shape = CircleShape
            ),
        onClick = clickEvent
    ) {
        Icon(
            painter = painterResource(imageId),
            tint = Color.White,
            contentDescription = "icon_button"
        )
    }
}

@Preview
@Composable
fun testButtonView() {
    BasicButton(modifier = Modifier, text = "test", clickEvent = {})
    RoundIconButton(modifier = Modifier, imageId = R.drawable.photo_camera_white, clickEvent = {})
}