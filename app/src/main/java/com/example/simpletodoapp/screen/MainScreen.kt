package com.example.simpletodoapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpletodoapp.R
import com.example.simpletodoapp.component.BasicTextTitle
import com.example.simpletodoapp.component.RoundImage
import com.example.simpletodoapp.component.TodoItem
import com.example.simpletodoapp.ui.theme.Highlight
import com.example.simpletodoapp.ui.theme.White

@Composable
fun MainScreen(

) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(end = 20.dp, bottom = 40.dp),
                shape = CircleShape,
                containerColor = Highlight,
                contentColor = White,
                onClick = {
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_white),
                    contentDescription = "add_task"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(color = White)
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 40.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BasicTextTitle(
                    modifier = Modifier,
                    text = stringResource(id = R.string.str_list_title)
                )
                RoundImage(
                    modifier = Modifier,
                    imageId = R.drawable.profile,
                    size = 80
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            TaskLazyColumn(taskList = List(20) { i -> "test" }) //todo room 값 가져오기
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskLazyColumn(
    taskList: List<String> //todo data class 조정
) {
    var alignment: Alignment = Alignment.Center
    var iconId: Int = R.drawable.edit_white
    var itemColor: Color = White
    LazyColumn(
        modifier = Modifier
    ) {
        items(
            items = taskList,
            key = null
        ) {
            val dismissState = rememberSwipeToDismissBoxState(positionalThreshold = { it * 0.9f })

            when (dismissState.dismissDirection) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    iconId = R.drawable.task_alt_white
                    alignment = Alignment.CenterStart
                    itemColor = Highlight
                    // todo remove
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    iconId = R.drawable.edit_white
                    alignment = Alignment.CenterEnd
                    itemColor = Highlight
                    // todo move edit screen
                }

                SwipeToDismissBoxValue.Settled -> {
                    alignment = Alignment.Center
                    itemColor = White
                }
            }
            SwipeToDismissBox(
                modifier = Modifier,
                state = dismissState,
                backgroundContent = {
                    Box(
                        contentAlignment = alignment,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = itemColor)
                    ) {
                        Icon(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            painter = painterResource(id = iconId),
                            tint = White,
                            contentDescription = "swipe_icon"
                        )
                    }
                }
            ) {
                TodoItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White),
                    task = "test task",
                    date = "YYYY MM DD HH:MM pm"
                )
            }
        }
    }
}

@Preview
@Composable
fun testMainScreen() {
    MainScreen()
}