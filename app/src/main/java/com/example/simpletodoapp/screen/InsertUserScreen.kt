package com.example.simpletodoapp.screen

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
import com.example.simpletodoapp.R
import com.example.simpletodoapp.component.BasicButton
import com.example.simpletodoapp.component.BasicEditText
import com.example.simpletodoapp.component.RoundIconButton

@Composable
fun InsertScreen(
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .padding(
                top = 100.dp,
                bottom = 50.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        RoundIconButton(
            modifier = Modifier,
            imageId = R.drawable.photo_camera_white,
        ) {
            //todo 카메라 intent 추가
        }
        Column {
            BasicEditText(
                modifier = Modifier,
                hint = stringResource(id = R.string.str_hint_name)
            )
            Spacer(modifier = Modifier.height(30.dp))
            BasicEditText(
                modifier = Modifier,
                hint = stringResource(id = R.string.str_hint_birthday)
            )
        }
        BasicButton(
            modifier = Modifier,
            text = stringResource(id = R.string.str_btn_register)
        ) {
            onRegisterClick()
        }
    }
}