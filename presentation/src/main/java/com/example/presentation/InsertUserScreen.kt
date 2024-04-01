package com.example.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.presentation.component.BasicButton
import com.example.presentation.component.BasicDatePickerButton
import com.example.presentation.component.BasicEditText
import com.example.presentation.component.RoundIconButton
import com.example.presentation.utils.getImageUri

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun InsertScreen(
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current
    var uriState by remember { mutableStateOf<Uri?>(null) }
    val cameraPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { tackPicture ->
            if(tackPicture != null){
                uriState = getImageUri(context, tackPicture,"camera")
            }
        }
    )
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
        if(uriState == null){
            RoundIconButton(
                modifier = Modifier,
                imageId = R.drawable.photo_camera_white,
            ) {
                cameraPickerLauncher.launch()
            }
        } else {
            GlideImage(
                model = uriState,
                contentDescription = "camera_image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .clickable {
                        cameraPickerLauncher.launch()
                    }
            )
        }
        Column {
            BasicEditText(
                modifier = Modifier,
                hint = stringResource(id = R.string.str_hint_name),
                updateText = {

                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            BasicDatePickerButton(
                modifier = Modifier,
                hint = stringResource(id = R.string.str_hint_birthday),
                updateDate = {

                }
            )
        }
        BasicButton(
            modifier = Modifier,
            text = stringResource(id = R.string.str_btn_register)
        ) {
            //if(uriState != null)
                onRegisterClick()
        }
    }
}