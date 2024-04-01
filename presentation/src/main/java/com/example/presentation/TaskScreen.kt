package com.example.presentation

import android.annotation.SuppressLint
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
import com.example.domain.model.TodoModel
import com.example.presentation.component.BasicButton
import com.example.presentation.component.BasicDatePickerButton
import com.example.presentation.component.BasicEditText
import com.example.presentation.component.BasicTextTitle

enum class TaskScreenType {
    ADD, EDIT
}

@Composable
fun WriteTaskScreen(
    mainViewModel: MainViewModel,
    screenType: TaskScreenType,
    task: String = "",
    date: String = "",
    moveBackStack: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 40.dp,
                vertical = 50.dp
            ),
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
                preText = task,
                updateText = {

                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            BasicDatePickerButton(
                modifier = Modifier,
                hint = if (screenType == TaskScreenType.ADD) {
                    stringResource(id = R.string.str_hint_date)
                } else {
                    date
                },
                updateDate = {

                }
            )
        }
        BasicButton(
            modifier = Modifier,
            text = if (screenType == TaskScreenType.ADD) stringResource(id = R.string.str_btn_add) else stringResource(id = R.string.str_btn_edit)
        ) {
            mainViewModel.insertTodo(
                todo = TodoModel(
                    id = 100,
                    task = "test",
                    date = "testDate"
                )
            )
            moveBackStack()
        }
    }
}