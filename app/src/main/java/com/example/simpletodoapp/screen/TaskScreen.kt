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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpletodoapp.R
import com.example.simpletodoapp.component.BasicButton
import com.example.simpletodoapp.component.BasicEditText
import com.example.simpletodoapp.component.BasicTextTitle

enum class TaskScreenType {
    ADD, EDIT
}

@Composable
fun WriteTaskScreen(
    screenType: TaskScreenType,
    task: String = "",
    date: String = "",
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            BasicTextTitle(
                modifier = Modifier,
                text = if (screenType == TaskScreenType.ADD) stringResource(id = R.string.str_title_add) else stringResource(id = R.string.str_title_edit)
            )
            Spacer(modifier = Modifier.height(30.dp))
            BasicEditText(
                modifier = Modifier,
                hint = if (screenType == TaskScreenType.ADD) {
                    stringResource(id = R.string.str_hint_task)
                } else {
                    ""
                },
                preText = task
            )
            Spacer(modifier = Modifier.height(30.dp))
            BasicEditText(
                modifier = Modifier,
                hint = if (screenType == TaskScreenType.ADD) {
                    stringResource(id = R.string.str_hint_date)
                } else {
                    ""
                },
                preText = date
            )
        }
        BasicButton(
            modifier = Modifier,
            text = if (screenType == TaskScreenType.ADD) stringResource(id = R.string.str_btn_add) else stringResource(id = R.string.str_btn_edit)
        ) {

        }
    }
}

@Preview
@Composable
fun testTaskScreen() {
    //WriteTaskScreen(screenType = TaskScreenType.ADD, modifier = Modifier)
    WriteTaskScreen(screenType = TaskScreenType.EDIT, task = "test task", date = "2022 / 03 / 22 pm", modifier = Modifier)
}