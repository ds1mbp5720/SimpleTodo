package com.example.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.presentation.component.BasicButton
import com.example.presentation.component.BasicTextBodyLarge
import com.example.presentation.component.BasicTextBodyMedium

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CheckProfileScreen(
    mainViewModel: MainViewModel,
    onStartClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    mainViewModel.getUsrInfo(context)
    val userInfo = mainViewModel.userInfo.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BasicButton(
            modifier = Modifier
                .padding(horizontal = 40.dp),
            text = stringResource(id = R.string.str_btn_insert)
        ) {
            onBackClick()
        }
        GlideImage(
            model = userInfo.value?.image?.toUri(),
            contentDescription = "camera_image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
        )
        Column {
            userInfo.value?.name?.let {
                BasicTextBodyLarge(
                    modifier = Modifier.wrapContentWidth(),
                    text = it
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            userInfo.value?.birthday?.let {
                BasicTextBodyMedium(
                    modifier = Modifier.wrapContentWidth(),
                    text = it
                )
            }
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