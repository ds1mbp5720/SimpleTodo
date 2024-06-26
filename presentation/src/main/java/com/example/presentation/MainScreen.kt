package com.example.presentation

import android.app.Activity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.model.TodoModel
import com.example.presentation.component.BasicTextTitle
import com.example.presentation.component.TodoItem
import com.example.presentation.theme.Highlight
import com.example.presentation.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(),
    onAddTaskClick: () -> Unit,
    onEditTaskClick: (Long) -> Unit
) {
    mainViewModel.getTodoList()
    val todoList = mainViewModel.todoList.observeAsState()
    val userInfo = mainViewModel.userInfo.observeAsState()
    val activity = LocalContext.current as Activity
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(end = 20.dp, bottom = 40.dp),
                shape = CircleShape,
                containerColor = Highlight,
                contentColor = White,
                onClick = onAddTaskClick
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
                GlideImage(
                    model = userInfo.value?.image?.toUri(),
                    contentDescription = "camera_image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            todoList.value?.let { todoList ->
                TaskLazyColumn(
                    onEditTask = onEditTaskClick,
                    onDeleteTask = { todo ->
                        mainViewModel.deleteTodo(todo)
                        mainViewModel.getTodoList()
                    },
                    todoList = todoList.sortedBy { todo -> todo.date }
                )
            }
        }
    }
    BackHandler(
        enabled = true
    ) {
        activity.finish()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskLazyColumn(
    onEditTask: (Long) -> Unit,
    onDeleteTask: (TodoModel) -> Unit,
    todoList: List<TodoModel>
) {
    val coroutine = rememberCoroutineScope()
    var alignment: Alignment = Alignment.Center
    var iconId: Int = R.drawable.edit_white
    var itemColor: Color = White
    LazyColumn(
        modifier = Modifier
    ) {
        items(
            items = todoList,
            key = null
        ) {
            val dismissState = rememberSwipeToDismissBoxState(
                positionalThreshold = { it * 0.4f },
                confirmValueChange = { dismissValue ->
                    when (dismissValue) {
                        SwipeToDismissBoxValue.StartToEnd -> {
                            onDeleteTask.invoke(it)
                            true
                        }

                        SwipeToDismissBoxValue.EndToStart -> {
                            onEditTask.invoke(it.id)
                            true
                        }

                        SwipeToDismissBoxValue.Settled -> {
                            true
                        }
                    }
                }
            )
            when (dismissState.dismissDirection) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    iconId = R.drawable.task_alt_white
                    alignment = Alignment.CenterStart
                    itemColor = Highlight
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    iconId = R.drawable.edit_white
                    alignment = Alignment.CenterEnd
                    itemColor = Highlight
                }

                SwipeToDismissBoxValue.Settled -> {
                    alignment = Alignment.Center
                    itemColor = White
                }
            }
            // 좌측 swipe 시 위치 원복
            if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart || dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd) {
                coroutine.launch {
                    dismissState.reset()
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
                    task = it.task,
                    date = it.date
                )
            }
        }
    }
}