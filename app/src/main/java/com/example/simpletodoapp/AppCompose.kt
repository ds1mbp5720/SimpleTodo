package com.example.simpletodoapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.simpletodoapp.navigation.MainDestination
import com.example.simpletodoapp.navigation.rememberMainNavController
import com.example.simpletodoapp.screen.CheckProfileScreen
import com.example.simpletodoapp.screen.InsertScreen
import com.example.simpletodoapp.screen.MainScreen
import com.example.simpletodoapp.screen.TaskScreenType
import com.example.simpletodoapp.screen.WriteTaskScreen
import com.example.simpletodoapp.ui.theme.SimpleTodoAppTheme

@Composable
fun SimpleTodoAppCompose() {
    val mainNavController = rememberMainNavController()
    SimpleTodoAppTheme {
        val mainViewModel: MainViewModel = viewModel()
        NavHost(
            navController = mainNavController.navController,
            startDestination = MainDestination.INSERT_USER
        ) {
            composable(MainDestination.MAIN) { from ->
                MainScreen(
                    mainViewModel = mainViewModel,
                    onAddTaskClick = { mainNavController.navigateToTask(from) },
                    onEditTaskClick = { mainNavController.navigateToTask(from) }
                )
            }
            composable(MainDestination.TASK) { from ->
                WriteTaskScreen(
                    mainViewModel = mainViewModel,
                    screenType = TaskScreenType.EDIT,
                    task = "",
                    date = "",
                    moveBackStack = { mainNavController.navController.navigateUp() }
                )
            }
            composable(MainDestination.INSERT_USER) { from ->
                InsertScreen(
                    onRegisterClick = { mainNavController.navigateToCheckProfile(from) }
                )
            }
            composable(MainDestination.CHECK_PROFILE) { from ->
                CheckProfileScreen(
                    onStartClick = { mainNavController.navigateToMain(from) }
                )
            }
        }
    }
}