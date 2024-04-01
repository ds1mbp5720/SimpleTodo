package com.example.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.model.TodoModel
import com.example.presentation.component.BasicButton
import com.example.presentation.component.BasicDatePickerButton
import com.example.presentation.component.BasicEditText
import com.example.presentation.component.BasicTextBodySmall
import com.example.presentation.component.BasicTextTitle

enum class TaskScreenType {
    ADD, EDIT
}

@Composable
fun WriteTaskScreen(
    mainViewModel: MainViewModel,
    screenType: TaskScreenType,
    id: Long = 0,
    moveBackStack: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {

    val todo = mainViewModel.findTodo(id)
    var editTask by remember { mutableStateOf(todo?.task ?: "") }
    var editDate by remember { mutableStateOf(todo?.date ?: "") }
    var showTaskEmpty by remember { mutableStateOf(false) }
    var showDateEmpty by remember { mutableStateOf(false) }
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
            BasicEditText(
                modifier = Modifier,
                hint = if (screenType == TaskScreenType.ADD) {
                    stringResource(id = R.string.str_hint_task)
                } else {
                    ""
                },
                preText = todo?.task ?: "",
                empty = showTaskEmpty,
                emptyText = stringResource(id = R.string.str_empty_task),
                emptyModifier = Modifier
                    .height(30.dp),
                updateText = {
                    editTask = it
                }
            )
            BasicDatePickerButton(
                modifier = Modifier,
                hint = if (screenType == TaskScreenType.ADD) {
                    stringResource(id = R.string.str_hint_date)
                } else {
                    todo?.date ?: ""
                },
                useTime = true,
                empty = showDateEmpty,
                emptyText = stringResource(id = R.string.str_empty_date),
                emptyModifier = Modifier
                    .height(30.dp),
                updateDate = {
                    editDate = it
                }
            )
            if(showDateEmpty){
                BasicTextBodySmall(
                    modifier = Modifier
                        .height(30.dp),
                    text = "Date is Empty",
                    color = Color.Red
                )
            } else {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
        BasicButton(
            modifier = Modifier,
            text = if (screenType == TaskScreenType.ADD) stringResource(id = R.string.str_btn_add) else stringResource(id = R.string.str_btn_edit)
        ) {
            showTaskEmpty = editTask.isEmpty()
            showDateEmpty = editDate.isEmpty()
            if(editTask.isNotEmpty() && editDate.isNotEmpty()) {
                mainViewModel.insertTodo(
                    todo = TodoModel(
                        id = System.currentTimeMillis(),
                        task = editTask,
                        date = editDate
                    )
                )
                moveBackStack()
            }
        }
    }
}